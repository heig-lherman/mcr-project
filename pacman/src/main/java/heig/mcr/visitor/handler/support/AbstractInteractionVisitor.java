package heig.mcr.visitor.handler.support;

import heig.mcr.visitor.game.actor.Pellet;
import heig.mcr.visitor.game.actor.Player;
import heig.mcr.visitor.game.actor.SuperPellet;
import heig.mcr.visitor.game.actor.npc.RandomGhost;
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
    public void interactWith(RandomGhost ghost) {
    }
}
