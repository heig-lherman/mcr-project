package heig.mcr.visitor.board;

import heig.mcr.visitor.math.Direction;

/**
 * An entity is placed on the board.
 */
public abstract class MovableEntity extends Entity {
    protected String name;
    public abstract int getMoveInterval();
    public abstract Direction getNextMove();

    @Override
    public String toString(){
        return name;
    }
}
