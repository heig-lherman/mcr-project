package heig.mcr.visitor.game.actor.npc;

import heig.mcr.visitor.board.Interactable;
import heig.mcr.visitor.game.sprite.PacmanSprites;
import heig.mcr.visitor.handler.InteractionVisitor;
import heig.mcr.visitor.math.Direction;
import heig.mcr.visitor.window.sprite.AnimatedSprite;

import java.util.Map;

public class Sith extends Ghost {

    private static final Map<Direction, AnimatedSprite> EDIBLE_SPRITES = PacmanSprites.getInstance().getEdibleSith();
    private static final Map<Direction, AnimatedSprite> INVINCIBLE_SPRITES = PacmanSprites.getInstance().getSith();

    public Sith() {
        super();
        this.name = "Sith";
        this.pathUpdateInterval = 8;
    }

    @Override
    public void acceptInteraction(InteractionVisitor v) {
        v.interactWith(this);
    }

    @Override
    public void interactWith(Interactable other) { }

    @Override
    Map<Direction, AnimatedSprite> getEdibleSprites() {
        return EDIBLE_SPRITES;
    }

    @Override
    Map<Direction, AnimatedSprite> getInvincibleSprites() {
        return INVINCIBLE_SPRITES;
    }
}
