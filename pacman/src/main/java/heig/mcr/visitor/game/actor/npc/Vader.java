package heig.mcr.visitor.game.actor.npc;

import heig.mcr.visitor.board.Cell;
import heig.mcr.visitor.board.Interactable;
import heig.mcr.visitor.game.sprite.PacmanSprites;
import heig.mcr.visitor.handler.InteractionVisitor;
import heig.mcr.visitor.math.Direction;
import heig.mcr.visitor.window.sprite.AnimatedSprite;

import java.util.Map;

public class Vader extends Ghost {

    private static final Map<Direction, AnimatedSprite> EDIBLE_SPRITES = PacmanSprites.getInstance().getEdibleVader();
    private static final Map<Direction, AnimatedSprite> INVINCIBLE_SPRITES = PacmanSprites.getInstance().getVader();

    private final InteractionVisitor handler = new VaderInteractionHandler();

    public Vader(Cell initialCell) {
        super(initialCell, 6);
    }

    @Override
    Map<Direction, AnimatedSprite> getEdibleSprites() {
        return EDIBLE_SPRITES;
    }

    @Override
    Map<Direction, AnimatedSprite> getInvincibleSprites() {
        return INVINCIBLE_SPRITES;
    }

    @Override
    public void acceptInteraction(InteractionVisitor v) {
        v.interactWith(this);
    }

    @Override
    public void interactWith(Interactable other) {
        other.acceptInteraction(handler);
    }

    private class VaderInteractionHandler extends GhostInteractionHandler {
    }

    @Override
    public String toString() {
        return "Vader";
    }
}
