package heig.mcr.visitor.game.actor;

import heig.mcr.visitor.board.Cell;
import heig.mcr.visitor.board.Interactable;
import heig.mcr.visitor.board.Interactor;
import heig.mcr.visitor.board.MovableEntity;
import heig.mcr.visitor.game.actor.handler.PlayerNormalVisitor;
import heig.mcr.visitor.game.actor.handler.PlayerVisitor;
import heig.mcr.visitor.handler.InteractionVisitor;
import heig.mcr.visitor.math.Direction;
import heig.mcr.visitor.window.sprite.Sprite;

public class Player extends MovableEntity implements Interactor {

    private static final int DEFAULT_INTERVAL = 300;

    private PlayerVisitor state = new PlayerNormalVisitor(this);
    private Direction requestedDirection = Direction.UP;

    private int moveInterval = DEFAULT_INTERVAL;

    public Player(Cell initialCell) {
        super(initialCell);
    }

    public boolean isAlive() {
        return state.isInteractable();
    }

    public boolean isScary() {
        return state.isScary();
    }

    @Override
    public Sprite getSprite() {
        return state.getSprite();
    }

    public void setState(PlayerVisitor state) {
        System.out.println("Player state: " + state.getClass().getSimpleName());
        this.state.dispose();
        this.state = state;
    }

    public void setRequestedDirection(Direction requestedDirection) {
        this.requestedDirection = requestedDirection;
    }

    @Override
    public int getMoveInterval() {
        return moveInterval;
    }

    public void setMoveInterval(int moveInterval) {
        this.moveInterval = moveInterval;
    }

    @Override
    public Direction getNextMove() {
        if (getCell().getNeighbor(requestedDirection).isWalkableBy(this)) {
            return requestedDirection;
        }

        return getDirection();
    }

    public void kill() {
        state.kill();
    }

    @Override
    public int getLayer() {
        return 100;
    }

    @Override
    public void accept(InteractionVisitor v) {
        v.visit(this);
    }

    @Override
    public void interactWith(Interactable other) {
        other.accept(state);
    }

    @Override
    public String toString() {
        return "Player";
    }
}
