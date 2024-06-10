package heig.mcr.visitor.game.actor.npc;

import heig.mcr.visitor.GameWindow;
import heig.mcr.visitor.board.Cell;
import heig.mcr.visitor.board.Interactor;
import heig.mcr.visitor.board.MovableEntity;
import heig.mcr.visitor.game.actor.Player;
import heig.mcr.visitor.handler.support.AbstractInteractionVisitor;
import heig.mcr.visitor.math.Direction;
import heig.mcr.visitor.math.Pathfinding;
import heig.mcr.visitor.math.RandomGenerator;
import heig.mcr.visitor.window.sprite.AnimatedSprite;
import heig.mcr.visitor.window.sprite.Sprite;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class Ghost extends MovableEntity implements Interactor {

    private List<Direction> pathToTarget = Collections.emptyList();
    private int pathIndex = 0;
    private int moveCounter = 0;

    private boolean blinking = false;
    private boolean goingHome = false;
    private final int pathUpdateInterval;

    protected Ghost(Cell initialCell, int pathUpdateInterval) {
        super(initialCell);
        this.pathUpdateInterval = pathUpdateInterval;
    }

    abstract Map<Direction, AnimatedSprite> getEdibleSprites();
    abstract Map<Direction, AnimatedSprite> getInvincibleSprites();

    @Override
    public Sprite getSprite() {
        boolean hasScaryPlayer = GameWindow.getInstance().getActiveLevel().hasScaryPlayer();
        if (hasScaryPlayer) {
            return getEdibleSprites().get(getDirection());
        } else {
            return getInvincibleSprites().get(getDirection());
        }
    }

    public void toggleBlinking() {
        if (blinking) {
            getEdibleSprites().values().forEach(AnimatedSprite::stopBlinking);
        } else {
            getEdibleSprites().values().forEach(AnimatedSprite::startBlinking);
        }

        blinking = !blinking;
    }

    @Override
    public int getMoveInterval() {
        if (goingHome) {
            return 50;
        }

        return RandomGenerator.getInstance().nextInt(350, 500);
    }

    @Override
    public Direction getNextMove() {
        moveCounter++;
        if (moveCounter % pathUpdateInterval == 1 && !goingHome) {
            updatePathToPlayer();
        }

        if (!pathToTarget.isEmpty() && pathIndex < pathToTarget.size()) {
            Direction nextMove = pathToTarget.get(pathIndex);
            pathIndex++;
            return nextMove;
        }

        if (goingHome) {
            goingHome = false;
            updatePathToPlayer();
        }

        // Default to random direction if no path found
        return Direction.random();
    }

    public void sendHome() {
        goingHome = true;
        updatePathToHome();
    }

    private void updatePathTo(Cell target) {
        pathToTarget = Pathfinding.findShortestPath(this.getCell(), target, this);
        pathIndex = 0;
    }

    private void updatePathToHome() {
        updatePathTo(getInitialCell());
    }

    private void updatePathToPlayer() {
        Player player = Pathfinding.findNearestEntity(Player.class, this.getCell());
        if (player != null) {
            updatePathTo(player.getCell());
        } else {
            pathToTarget = List.of();
        }
    }

    @Override
    public int getLayer() {
        return 10;
    }

    protected class GhostInteractionVisitor extends AbstractInteractionVisitor {
        @Override
        public void visit(Player player) {
            if (!player.isScary() && !goingHome) {
                System.out.printf("Player killed by %s%n", Ghost.this);
                player.kill();
            }
        }
    }
}
