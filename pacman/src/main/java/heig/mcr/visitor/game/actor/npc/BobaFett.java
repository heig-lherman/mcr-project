package heig.mcr.visitor.game.actor.npc;

import heig.mcr.visitor.board.Cell;
import heig.mcr.visitor.board.Interactable;
import heig.mcr.visitor.game.sprite.PacmanSprites;
import heig.mcr.visitor.handler.InteractionVisitor;
import heig.mcr.visitor.math.Direction;
import heig.mcr.visitor.window.sprite.AnimatedSprite;

import java.util.Map;

public class BobaFett extends Ghost {

    private static final Map<Direction, AnimatedSprite> EDIBLE_SPRITES = PacmanSprites.getInstance().getEdibleBobaFett();
    private static final Map<Direction, AnimatedSprite> INVINCIBLE_SPRITES = PacmanSprites.getInstance().getBobaFett();

    private int moveInterval = 300; // starts faster than the others

    public BobaFett(Cell initialCell) {
        super(initialCell, 8);
    }

    @Override
    Map<Direction, AnimatedSprite> getEdibleSprites() {
        return EDIBLE_SPRITES;
    }

    @Override
    Map<Direction, AnimatedSprite> getInvincibleSprites() {
        return INVINCIBLE_SPRITES;
    }

    public void moveFaster() {
        if (moveInterval - 50 > 0) {
            moveInterval -= 50;
        }
    }

    @Override
    public int getMoveInterval() {
        return moveInterval;
    }

    @Override
    public void accept(InteractionVisitor v) {
        v.visit(this);
    }

    @Override
    public void interactWith(Interactable other) {
        // Boba Fett does not interact with other NPCs
    }

    @Override
    public String toString() {
        return "Boba Fett";
    }
}
