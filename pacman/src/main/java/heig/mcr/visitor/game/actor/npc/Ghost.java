package heig.mcr.visitor.game.actor.npc;

import heig.mcr.visitor.board.Interactor;
import heig.mcr.visitor.board.MovableEntity;
import heig.mcr.visitor.math.Direction;
import heig.mcr.visitor.window.sprite.Sprite;

import java.util.Map;

public abstract class Ghost extends MovableEntity implements Interactor {

    private final Map<Direction, ? extends Sprite> directedSprites;

    protected Ghost(Map<Direction, ? extends Sprite> directedSprites) {
        this.directedSprites = directedSprites;
    }

    @Override
    public Sprite getSprite() {
        return directedSprites.get(getDirection());
    }

    @Override
    public int getMoveInterval() {
        // TODO should probably be randomized and ghost-specific
        return 500;
    }
}
