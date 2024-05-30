package heig.mcr.visitor.game;

import heig.mcr.visitor.board.Board;
import heig.mcr.visitor.board.Cell;
import heig.mcr.visitor.board.Entity;
import heig.mcr.visitor.board.Interactor;
import heig.mcr.visitor.game.actor.Pellet;
import heig.mcr.visitor.game.actor.Player;
import heig.mcr.visitor.game.actor.npc.Ghost;
import heig.mcr.visitor.math.Direction;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public final class Level {

    private final Object moveLock = new Object();
    private final Object startLock = new Object();

    private final Board board;

    private final Map<Ghost, ExecutorService> ghostThreads = new HashMap<>();
    private final List<LevelObserver> observers = new LinkedList<>();
    private final List<Player> players;

    private boolean running = false;

    public Level(Board board, Collection<Ghost> ghosts, List<Player> players) {
        this.board = board;
        this.players = players;
        for (var ghost : ghosts) {
            ghostThreads.put(ghost, null);
        }
    }

    private void addObserver(LevelObserver observer) {
        observers.add(observer);
    }

    private void removeObserver(LevelObserver observer) {
        observers.remove(observer);
    }

    public void move(Entity entity, Direction direction) {
        if (!running) {
            return;
        }

        synchronized (moveLock) {
            entity.setDirection(direction);

            Cell location = entity.getCell();
            Cell destination = location.getNeighbor(direction);

            if (destination.isWalkable()) {
                Collection<Entity> occupants = destination.getOccupants();
                entity.setCell(destination);

                if ((entity instanceof Interactor i)) {
                    for (Entity occupant : occupants) {
                        i.interactWith(occupant);
                    }
                }
            }

            updateObservers();
        }
    }

    public void start() {
        if (running) {
            return;
        }

        synchronized (startLock) {
            running = true;
            startThreads();
            updateObservers();
        }
    }

    public void stop() {
        if (!running) {
            return;
        }

        synchronized (startLock) {
            stopThreads();
            running = false;
        }
    }

    private void startThreads() {
        for (var ghost : ghostThreads.keySet()) {
            var service = Executors.newSingleThreadScheduledExecutor();
            service.scheduleAtFixedRate(
                    new GhostTask(ghost),
                    ghost.getMoveInterval() / 2,
                    ghost.getMoveInterval(),
                    TimeUnit.MILLISECONDS
            );

            ghostThreads.put(ghost, service);
        }
    }

    private void stopThreads() {
        for (var service : ghostThreads.values()) {
            service.shutdownNow();
        }
    }

    private void updateObservers() {
        if (!hasAlivePlayer()) {
            observers.forEach(LevelObserver::onLevelLost);
        } else if (countRemainingPellets() == 0) {
            observers.forEach(LevelObserver::onLevelWon);
        }
    }

    public boolean hasAlivePlayer() {
        return players.stream().anyMatch(Player::isAlive);
    }

    public long countRemainingPellets() {
        return board.streamCells()
                .flatMap(cell -> cell.getOccupants().stream())
                .filter(Pellet.class::isInstance)
                .count();
    }

    private class GhostTask implements Runnable {

        private final Ghost ghost;

        public GhostTask(Ghost ghost) {
            this.ghost = ghost;
        }

        @Override
        public void run() {
            Direction nextMove = ghost.nextMove();
            if (Objects.nonNull(nextMove)) {
                move(ghost, nextMove);
            }
        }
    }

    public interface LevelObserver {

        void onLevelWon();

        void onLevelLost();
    }
}
