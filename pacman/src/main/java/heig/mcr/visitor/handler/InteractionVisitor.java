package heig.mcr.visitor.handler;

import heig.mcr.visitor.game.actor.Pellet;
import heig.mcr.visitor.game.actor.Player;
import heig.mcr.visitor.game.actor.SuperPellet;
import heig.mcr.visitor.game.actor.npc.RandomGhost;

public interface InteractionVisitor {

    void interactWith(Player player);

    // Pellets
    void interactWith(Pellet pellet);
    void interactWith(SuperPellet pellet);

    // Ghosts
    void interactWith(RandomGhost ghost);
}
