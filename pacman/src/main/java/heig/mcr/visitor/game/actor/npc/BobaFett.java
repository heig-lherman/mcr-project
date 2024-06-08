package heig.mcr.visitor.game.actor.npc;

import heig.mcr.visitor.board.Interactable;
import heig.mcr.visitor.game.sprite.PacmanSprites;
import heig.mcr.visitor.handler.InteractionVisitor;
import heig.mcr.visitor.math.Direction;
import heig.mcr.visitor.window.sprite.AnimatedSprite;
import java.util.Map;

public class BobaFett extends Ghost {
    public BobaFett() {
        super();
        this.name = "Boba Fett";
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
        return PacmanSprites.getInstance().getEdibleBobaFett();
    }

    @Override
    Map<Direction, AnimatedSprite> getInvincibleSprites() {
        return PacmanSprites.getInstance().getBobaFett();
    }
}
