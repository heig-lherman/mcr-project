package heig.mcr.visitor.game.actor.npc;

import heig.mcr.visitor.board.Entity;
import heig.mcr.visitor.board.Interactor;
import heig.mcr.visitor.math.Direction;

public abstract class Ghost extends Entity implements Interactor {

    public abstract Direction nextMove();

    public int getMoveInterval() {
        // TODO should probably be randomized and ghost-specific
        return 500;
    }
}
