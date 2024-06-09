package heig.mcr.visitor.handler.support;

import heig.mcr.visitor.game.actor.Pellet;
import heig.mcr.visitor.game.actor.Player;
import heig.mcr.visitor.game.actor.SuperPellet;
import heig.mcr.visitor.game.actor.npc.*;
import heig.mcr.visitor.handler.InteractionVisitor;

public abstract class AbstractInteractionVisitor implements InteractionVisitor {

    @Override
    public void interactWith(Player player) {
    }

    @Override
    public void interactWith(Pellet pellet) {
    }

    @Override
    public void interactWith(SuperPellet superPellet) {
        this.interactWith((Pellet) superPellet);
    }

    @Override
    public void interactWith(Vader vader) {
    }

    @Override
    public void interactWith(Luke luke) {
    }

    @Override
    public void interactWith(Sith sith) {
    }

    @Override
    public void interactWith(StormTrooper stormTrooper) {
    }

    @Override
    public void interactWith(BobaFett bobaFett) {
    }
}
