package heig.mcr.visitor.game.actor.state;

import heig.mcr.visitor.game.actor.npc.Ghost;

class EdibleState extends GhostState {
    public EdibleState(Ghost ghost) {
        super(ghost);
    }

    @Override
    public boolean isEdible() {
        return true;
    }

    @Override
    public void becomeEdible() {
    }

    @Override
    public void becomeInvincible() {
        ghost.setState(new InvincibleState(ghost));
    }

}

