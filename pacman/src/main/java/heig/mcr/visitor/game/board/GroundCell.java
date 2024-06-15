package heig.mcr.visitor.game.board;

import heig.mcr.visitor.board.Cell;
import heig.mcr.visitor.board.Entity;
import heig.mcr.visitor.window.sprite.DefaultSprite;
import heig.mcr.visitor.window.sprite.Sprite;

/**
 * A cell representing the ground, can be walked through by anyone.
 *
 * @author Loïc Herman
 * @author Massimo Stefani
 * @author Samuel Roland
 * @author Timothée Van Hove
 */
public class GroundCell extends Cell {

    @Override
    public boolean isWalkableBy(Entity _any) {
        return true;
    }

    @Override
    public Sprite getSprite() {
        return new DefaultSprite();
    }
}
