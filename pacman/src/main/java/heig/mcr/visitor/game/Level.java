package heig.mcr.visitor.game;

import heig.mcr.visitor.board.*;
import heig.mcr.visitor.game.actor.Pellet;
import heig.mcr.visitor.game.actor.Player;
import heig.mcr.visitor.game.actor.npc.Ghost;
import heig.mcr.visitor.math.Direction;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Level {

    private final Object moveLock = new Object();
    private final Object startLock = new Object();

    private final Board board;

    private final Map<MovableEntity, ExecutorService> entityThreads = new HashMap<>();
    private final List<Player> players = new LinkedList<>();
    private final List<LevelObserver> observers = new LinkedList<>();

    private boolean running = false;

    public Level(Board board, Collection<Ghost> ghosts, List<Player> players) {
        this.board = board;
        this.players.addAll(players);

        for (var ghost : ghosts) {
            entityThreads.put(ghost, Executors.newSingleThreadScheduledExecutor());
        }

        for (var player : players) {
            entityThreads.put(player, Executors.newSingleThreadScheduledExecutor());
        }
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer(int index) {
        return players.get(index);
    }

    public void move(Entity entity, Direction direction) {
        synchronized (moveLock) {
            if (!running) {
                return;
            }

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
        synchronized (startLock) {
            if (running) {
                return;
            }

            running = true;
            startThreads();
            updateObservers();
        }
    }

    public void stop() {
        synchronized (startLock) {
            if (!running) {
                return;
            }

            stopThreads();
            running = false;
        }
    }

    private void startThreads() {
        for (var entity : entityThreads.keySet()) {
            var service = Executors.newSingleThreadScheduledExecutor();
            service.scheduleAtFixedRate(
                    new EntityTask(entity),
                    entity.getMoveInterval() / 2,
                    entity.getMoveInterval(),
                    TimeUnit.MILLISECONDS
            );

            entityThreads.put(entity, service);
        }
    }

    private void stopThreads() {
        entityThreads.values().forEach(ExecutorService::shutdownNow);
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

    private class EntityTask implements Runnable {

        private final MovableEntity entity;

        public EntityTask(MovableEntity entity) {
            this.entity = entity;
        }

        @Override
        public void run() {
            Direction nextMove = entity.nextMove();
            if (Objects.nonNull(nextMove)) {
                move(entity, nextMove);
            }
        }
    }

    public interface LevelObserver {

        void onLevelWon();

        void onLevelLost();
    }
}
