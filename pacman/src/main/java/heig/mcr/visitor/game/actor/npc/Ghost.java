package heig.mcr.visitor.game.actor.npc;

import heig.mcr.visitor.board.Interactor;
import heig.mcr.visitor.board.MovableEntity;
import heig.mcr.visitor.game.actor.Player;
import heig.mcr.visitor.game.actor.state.GhostState;
import heig.mcr.visitor.game.actor.state.InvincibleState;
import heig.mcr.visitor.math.Direction;
import heig.mcr.visitor.math.Pathfinding;
import heig.mcr.visitor.window.sprite.AnimatedSprite;
import heig.mcr.visitor.window.sprite.Sprite;
import java.util.List;
import java.util.Map;

public abstract class Ghost extends MovableEntity implements Interactor {

    private GhostState state;
    private Map<Direction, ? extends Sprite> directedSprites;
    private List<Direction> pathToPlayer;
    private int pathIndex;
    private int moveCounter = 0;
    protected int pathUpdateInterval = 10;

    protected Ghost() {
        this.directedSprites = getInvincibleSprites();
        this.state = new InvincibleState(this);
        this.pathToPlayer = List.of();
        this.pathIndex = 0;
    }

    @Override
    public int getLayer() {
        return 10;
    }

    @Override
    public Sprite getSprite() {
        return directedSprites.get(getDirection());
    }

    @Override
    public int getMoveInterval() {
        // TODO should probably be randomized and ghost-specific
        return 500;
    }

    public void setState(GhostState state) {
        this.state = state;
        changeSprite();
    }

    public boolean isEdible() {
        return state.isEdible();
    }

    public void becomeEdible() {
        state.becomeEdible();
    }

    public void becomeInvincible() {
        state.becomeInvincible();
    }

    abstract Map<Direction, AnimatedSprite> getEdibleSprites();

    abstract Map<Direction, AnimatedSprite> getInvincibleSprites();

    private void changeSprite() {
        if (isEdible()){
            directedSprites = getEdibleSprites();
            for (Direction direction : directedSprites.keySet()) {
                directedSprites.get(direction).startBlinking();
            }
        } else {
            directedSprites = getInvincibleSprites();
            for (Direction direction : directedSprites.keySet()) {
                directedSprites.get(direction).stopBlinking();
            }
        }
    }

    @Override
    public Direction getNextMove(){
        moveCounter++;
        if (moveCounter % pathUpdateInterval == 1) {
            updatePathToPlayer();
        }

        if (!pathToPlayer.isEmpty() && pathIndex < pathToPlayer.size()) {
            Direction nextMove = pathToPlayer.get(pathIndex);
            pathIndex++;
            return nextMove;
        }

        // Default to random direction if no path found
        return Direction.random();
    }

    private void updatePathToPlayer() {
        Player player = Pathfinding.findNearestEntity(Player.class, this.getCell());
        if (player != null) {
            pathToPlayer = Pathfinding.findShortestPath(this.getCell(), player.getCell());
            pathIndex = 0;
        } else {
            pathToPlayer = List.of();
        }
    }
}