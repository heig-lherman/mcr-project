package heig.mcr.visitor.game.actor.npc;

import heig.mcr.visitor.board.Interactable;
import heig.mcr.visitor.game.sprite.PacmanSprites;
import heig.mcr.visitor.handler.InteractionVisitor;
import heig.mcr.visitor.math.Direction;
import heig.mcr.visitor.window.sprite.AnimatedSprite;

import java.util.Map;

public class Luke extends Ghost {

    private static final Map<Direction, AnimatedSprite> SPRITES = PacmanSprites.getInstance().getLuke();

    public Luke() {
        super();
        this.name = "Luke";
        this.pathUpdateInterval =4;
    }

    @Override
    public void acceptInteraction(InteractionVisitor v) {
        v.interactWith(this);
    }

    @Override
    public void interactWith(Interactable other) { }

    @Override
    Map<Direction, AnimatedSprite> getEdibleSprites() {
        return SPRITES;
    }

    @Override
    Map<Direction, AnimatedSprite> getInvincibleSprites() {
        return SPRITES;
    }

    @Override
    public void becomeEdible() { }
}
