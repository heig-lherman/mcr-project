package heig.mcr.visitor.game.actor.npc;

import heig.mcr.visitor.board.Cell;
import heig.mcr.visitor.board.Interactable;
import heig.mcr.visitor.game.sprite.PacmanSprites;
import heig.mcr.visitor.handler.InteractionVisitor;
import heig.mcr.visitor.math.Direction;
import heig.mcr.visitor.window.sprite.AnimatedSprite;

import java.util.Map;

/**
 * A ghost representing Luke.
 *
 * @author Loïc Herman
 * @author Massimo Stefani
 * @author Samuel Roland
 * @author Timothée Van Hove
 */
public class Luke extends Ghost {

    private static final Map<Direction, AnimatedSprite> SPRITES = PacmanSprites.getInstance().getLuke();

    private final InteractionVisitor handler = new LukeInteractionVisitor();

    public Luke(Cell initialCell) {
        super(initialCell, 4);
    }

    @Override
    Map<Direction, AnimatedSprite> getEdibleSprites() {
        return SPRITES;
    }

    @Override
    Map<Direction, AnimatedSprite> getInvincibleSprites() {
        return SPRITES;
    }

    @Override
    public void accept(InteractionVisitor v) {
        v.visit(this);
    }

    @Override
    public void interactWith(Interactable other) {
        other.accept(handler);
    }

    private class LukeInteractionVisitor extends GhostInteractionVisitor {
    }

    @Override
    public String toString() {
        return "Luke";
    }
}
