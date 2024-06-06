package heig.mcr.visitor.game.actor.state;

import heig.mcr.visitor.game.actor.npc.Ghost;

public abstract class GhostState {
    protected Ghost ghost;

    public GhostState(Ghost ghost) {
        this.ghost = ghost;
    }

    public abstract boolean isEdible();

    public abstract void becomeEdible();
    public abstract void becomeInvincible();
}
