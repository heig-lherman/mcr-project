package heig.mcr.visitor.handler;

import heig.mcr.visitor.game.actor.Pellet;
import heig.mcr.visitor.game.actor.Player;
import heig.mcr.visitor.game.actor.SuperPellet;
import heig.mcr.visitor.game.actor.npc.*;

public interface InteractionVisitor {

    void interactWith(Player player);

    // Pellets
    void interactWith(Pellet pellet);
    void interactWith(SuperPellet pellet);

    // Ghosts
    void interactWith(Vader vader);

    void interactWith(Luke luke);

    void interactWith(Sith sith);

    void interactWith(StormTrooper stormTrooper);

    void interactWith(BobaFett bobaFett);

}
