package heig.mcr.visitor.game.actor.npc;

import heig.mcr.visitor.board.Interactable;
import heig.mcr.visitor.handler.InteractionVisitor;
import heig.mcr.visitor.math.Direction;
import heig.mcr.visitor.window.sprite.Sprite;

import java.util.Map;

public class Sith extends Ghost {
    protected Sith(Map<Direction, ? extends Sprite> directedSprites) {
        super(directedSprites);
    }

    @Override
    public void acceptInteraction(InteractionVisitor v) {

    }

    @Override
    public void interactWith(Interactable other) {

    }

    @Override
    public Direction getNextMove() {
        return null;
    }
}
