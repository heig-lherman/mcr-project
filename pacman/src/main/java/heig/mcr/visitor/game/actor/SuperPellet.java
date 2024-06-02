package heig.mcr.visitor.game.actor;

import heig.mcr.visitor.game.sprite.PacmanSprites;
import heig.mcr.visitor.handler.InteractionVisitor;
import heig.mcr.visitor.window.sprite.Sprite;

public class SuperPellet extends Pellet {

    @Override
    public Sprite getSprite() {
        return PacmanSprites.getInstance().getSuperPellet();
    }

    @Override
    public void acceptInteraction(InteractionVisitor v) {
        v.interactWith(this);
    }
}
