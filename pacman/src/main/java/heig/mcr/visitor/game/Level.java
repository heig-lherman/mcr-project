package heig.mcr.visitor.game;

import heig.mcr.visitor.board.*;
import heig.mcr.visitor.game.actor.Pellet;
import heig.mcr.visitor.game.actor.Player;
import heig.mcr.visitor.game.actor.SuperPellet;
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

    private long superPelletCount;

    private boolean running = false;

    private int edibleDuration = 10000;
    private long edibleEndTime;


    public Level(Board board, Collection<Ghost> ghosts, List<Player> players) {
        this.board = board;
        this.players.addAll(players);

        for (var ghost : ghosts) {
            entityThreads.put(ghost, Executors.newSingleThreadScheduledExecutor());
        }

        for (var player : players) {
            entityThreads.put(player, Executors.newSingleThreadScheduledExecutor());
        }
        superPelletCount = countRemainingSuperPellets();
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer(int index) {
        return players.get(index);
    }

    public boolean isRunning() {
        return running;
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
            System.out.println("Starting thread for entity: " + entity);
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
        if (countRemainingSuperPellets() < superPelletCount) {
            superPelletCount = countRemainingSuperPellets();
            for (var ghost : entityThreads.keySet()) {
                if (ghost instanceof Ghost g) {
                    g.becomeEdible();
                    edibleEndTime = System.currentTimeMillis() + edibleDuration;
                }
            }
            players.forEach(Player::becomeSuper);
        }
        if (edibleEndTime != 0 && System.currentTimeMillis() > edibleEndTime) {
            for (var ghost : entityThreads.keySet()) {
                if (ghost instanceof Ghost g) {
                    g.becomeInvincible();
                }
            }
            players.forEach(Player::leaveSuper);
            edibleEndTime = 0;
        }
    }

    public void addObserver(LevelObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(LevelObserver observer) {
        observers.remove(observer);
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

    public long countRemainingSuperPellets() {
        return board.streamCells()
                .flatMap(cell -> cell.getOccupants().stream())
                .filter(SuperPellet.class::isInstance)
                .count();
    }

    private class EntityTask implements Runnable {
        private final MovableEntity entity;
        public EntityTask(MovableEntity entity) {
            this.entity = entity;
        }

        @Override
        public void run() {
            Direction nextMove = entity.getNextMove();
            if (Objects.nonNull(nextMove)) {
                move(entity, nextMove);
            } else {
                System.out.println("nextMove is null!");
            }
        }
    }

    public interface LevelObserver {
        void onLevelWon();
        void onLevelLost();
    }
}
