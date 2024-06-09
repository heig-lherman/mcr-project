package heig.mcr.visitor.board;

import heig.mcr.visitor.math.Direction;

/**
 * An entity is placed on the board.
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
