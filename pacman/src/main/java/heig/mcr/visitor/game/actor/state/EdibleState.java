package heig.mcr.visitor.game.actor.state;

import heig.mcr.visitor.game.actor.npc.Ghost;

class Mangeable extends GhostState {
    public Mangeable(Ghost ghost) {
        super(ghost);
    }

    @Override
    public boolean isMangeable() {
        return true;
    }

    @Override
    public void becomeMangeable() {
        // Déjà en état mangeable
    }

    @Override
    public void becomeInvincible() {
        ghost.setState(new Invincible(ghost));
    }

    @Override
    public void die() {
        ghost.setState(new Mort(ghost));
    }
}

// État Invincible
class Invincible extends GhostState {
    public Invincible(Ghost ghost) {
        super(ghost);
    }

    @Override
    public boolean isMangeable() {
        return false;
    }

    @Override
    public void becomeMangeable() {
        ghost.setState(new Mangeable(ghost));
    }

    @Override
    public void becomeInvincible() {
        // Déjà invincible
    }

    @Override
    public void die() {
        ghost.setState(new Mort(ghost));
    }
}

// État Mort
class Mort extends GhostState {
    public Mort(Ghost ghost) {
        super(ghost);
    }

    @Override
    public boolean isMangeable() {
        return false;
    }

    @Override
    public void becomeMangeable() {
        // Ne peut pas devenir mangeable s'il est mort
    }

    @Override
    public void becomeInvincible() {
        ghost.setState(new Invincible(ghost));
    }

    @Override
    public void die() {
        // Déjà mort
    }
}

