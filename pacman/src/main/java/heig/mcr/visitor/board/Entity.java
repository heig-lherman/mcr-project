package heig.mcr.visitor.board;

import heig.mcr.visitor.math.Direction;
import heig.mcr.visitor.window.sprite.Sprite;

/**
 * An entity is placed on the board.
 */
public abstract class Entity implements Interactable {
    private Cell initialCell;

    private Cell cell;
    private Direction direction = Direction.UP;
    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        if (initialCell == null) {
            initialCell = cell;
        }
        if (this.cell != null) {
            this.cell.removeOccupant(this);
        }

        this.cell = cell;
        cell.addOccupant(this);
    }

    public void leaveCell() {
        if (cell != null) {
            cell.removeOccupant(this);
            cell = null;
        }
    }

    public Cell getInitialCell() {
        return initialCell;
    }

    public abstract int getLayer();
    public abstract Sprite getSprite();
}
