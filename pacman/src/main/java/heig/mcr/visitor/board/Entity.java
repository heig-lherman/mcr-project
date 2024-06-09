package heig.mcr.visitor.board;

import heig.mcr.visitor.game.actor.SpeechBubble;
import heig.mcr.visitor.math.Direction;
import heig.mcr.visitor.window.sprite.Sprite;

/**
 * An entity is placed on the board.
 */
public abstract class Entity implements Interactable {

    private Cell cell;
    private Direction direction = Direction.UP;
    private final Cell initialCell;

    protected Entity(Cell initialCell) {
        this.initialCell = initialCell;
        this.cell = initialCell;

        this.initialCell.addOccupant(this);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Cell getInitialCell() {
        return initialCell;
    }

    public Cell getCell() {
        return cell;
    }

    protected void setCell(Cell cell) {
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

    public void spawnSpeech(String text) {
        new SpeechBubble(cell.getNeighbor(Direction.UP), text);
    }

    public abstract int getLayer();
    public abstract Sprite getSprite();
}
