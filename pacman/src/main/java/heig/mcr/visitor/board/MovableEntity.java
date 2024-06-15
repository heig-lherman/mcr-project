package heig.mcr.visitor.board;

import heig.mcr.visitor.math.Direction;

/**
 * An movable entity is an entity that can move on the board at regular intervals.
 *
 * @author Loïc Herman
 * @author Massimo Stefani
 * @author Samuel Roland
 * @author Timothée Van Hove
 */
public abstract class MovableEntity extends Entity {

    protected MovableEntity(Cell initialCell) {
        super(initialCell);
    }

    public abstract int getMoveInterval();
    public abstract Direction getNextMove();

    @Override
    public void setCell(Cell cell) {
        super.setCell(cell);
    }
}
