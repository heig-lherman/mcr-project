package heig.mcr.visitor.game.actor;

import heig.mcr.visitor.board.Cell;
import heig.mcr.visitor.game.sprite.PacmanSprites;
import heig.mcr.visitor.handler.InteractionVisitor;
import heig.mcr.visitor.window.sprite.Sprite;

public class SuperPellet extends Pellet {

    public SuperPellet(Cell initialCell) {
        super(initialCell);
    }

    @Override
    public Sprite getSprite() {
        return PacmanSprites.getInstance().getSuperPellet();
    }

    @Override
    public void accept(InteractionVisitor v) {
        v.visit(this);
    }
}
