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

    private final InteractionVisitor handler = new VaderInteractionVisitor();

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
    public void accept(InteractionVisitor v) {
        v.visit(this);
    }

    @Override
    public void interactWith(Interactable other) {
        other.accept(handler);
    }

    private class VaderInteractionVisitor extends GhostInteractionVisitor {

        @Override
        public void visit(Luke luke) {
            spawnSpeech("I am your father.");
        }
    }

    @Override
    public String toString() {
        return "Vader";
    }
}
