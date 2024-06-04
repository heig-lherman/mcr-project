package heig.mcr.visitor.game.actor.npc;

import heig.mcr.visitor.board.Interactable;
import heig.mcr.visitor.game.actor.Player;
import heig.mcr.visitor.game.sprite.PacmanSprites;
import heig.mcr.visitor.handler.InteractionVisitor;
import heig.mcr.visitor.math.Direction;
import heig.mcr.visitor.math.Pathfinding;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * A ghost that moves randomly.
 */
public class RandomGhost extends Ghost {

    public RandomGhost() {
        super(PacmanSprites.getInstance().getRedGhost());
    }

    @Override
    public Direction getNextMove() {
        return Optional.ofNullable(Pathfinding.findNearestEntity(Player.class, getCell()))
                .map(Player::getCell)
                .map(target -> Pathfinding.findShortestPath(getCell(), target))
                .map(List::stream)
                .flatMap(Stream::findFirst)
                .orElseGet(Direction::random);
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
