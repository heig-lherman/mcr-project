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

    private PlayerVisitor handler = new PlayerNormalVisitor(this);
    private Direction requestedDirection = Direction.UP;

    private int moveInterval = DEFAULT_INTERVAL;

    public Player(Cell initialCell) {
        super(initialCell);
    }

    public boolean isAlive() {
        return handler.isInteractable();
    }

    public boolean isScary() {
        return handler.isScary();
    }

    @Override
    public Sprite getSprite() {
        return handler.getSprite();
    }

    public void setHandler(PlayerVisitor handler) {
        System.out.println("Player state: " + handler.getClass().getSimpleName());
        this.handler.dispose();
        this.handler = handler;
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
        handler.kill();
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
        other.accept(handler);
    }

    @Override
    public String toString() {
        return "Player";
    }
}
