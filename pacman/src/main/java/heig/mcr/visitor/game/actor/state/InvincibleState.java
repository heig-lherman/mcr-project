package heig.mcr.visitor.game.actor.state;

import heig.mcr.visitor.game.actor.npc.Ghost;

public class InvincibleState extends GhostState {
    public InvincibleState(Ghost ghost) {
        super(ghost);
    }

    @Override
    public boolean isEdible() {
        return false;
    }

    @Override
    public void becomeEdible() {
        ghost.setState(new EdibleState(ghost));
    }

    @Override
    public void becomeInvincible() {
    }

}
