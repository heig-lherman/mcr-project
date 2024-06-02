package heig.mcr.visitor.game.actor.npc;

import heig.mcr.visitor.board.Interactable;
import heig.mcr.visitor.game.sprite.PacmanSprites;
import heig.mcr.visitor.handler.InteractionVisitor;
import heig.mcr.visitor.math.Direction;

/**
 * A ghost that moves randomly.
 */
public class RandomGhost extends Ghost {

    public RandomGhost() {
        super(PacmanSprites.getInstance().getRedGhost());
    }

    @Override
    public Direction getNextMove() {
        return Direction.random();
    }

    @Override
    public void acceptInteraction(InteractionVisitor v) {
        v.interactWith(this);
    }

    @Override
    public void interactWith(Interactable other) {
        // Do nothing
    }
}
