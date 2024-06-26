package heig.mcr.visitor.game.actor;

import heig.mcr.visitor.board.Cell;
import heig.mcr.visitor.board.Entity;
import heig.mcr.visitor.game.sprite.PacmanSprites;
import heig.mcr.visitor.handler.InteractionVisitor;
import heig.mcr.visitor.window.sprite.Sprite;

/**
 * A pellet that can be eaten by the player to increase the score.
 *
 * @author Loïc Herman
 * @author Massimo Stefani
 * @author Samuel Roland
 * @author Timothée Van Hove
 */
public class Pellet extends Entity {

    public Pellet(Cell initialCell) {
        super(initialCell);
    }

    @Override
    public int getLayer() {
        return 1;
    }

    @Override
    public Sprite getSprite() {
        return PacmanSprites.getInstance().getPellet();
    }

    @Override
    public void accept(InteractionVisitor v) {
        v.visit(this);
    }
}
