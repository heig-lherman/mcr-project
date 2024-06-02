package heig.mcr.visitor.board;

import heig.mcr.visitor.math.Direction;

/**
 * An entity is placed on the board.
 */
public abstract class MovableEntity extends Entity {

    public abstract int getMoveInterval();
    public abstract Direction nextMove();
}
