package heig.mcr.visitor.game.actor;

import heig.mcr.visitor.game.actor.npc.Ghost;

public abstract class GhostState {
    protected Ghost ghost;

    public GhostState(Ghost ghost) {
        this.ghost = ghost;
    }

    public abstract boolean isMangeable();

    public abstract void becomeMangeable();
    public abstract void becomeInvincible();
    public abstract void die();
}
