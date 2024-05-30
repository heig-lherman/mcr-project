package heig.mcr.visitor.handler;

import heig.mcr.visitor.game.actor.Pellet;
import heig.mcr.visitor.game.actor.Player;

public interface InteractionVisitor {

    void interactWith(Player player);

    void interactWith(Pellet player);

    // Ghosts

    // void interactWith(Pellet pacmanEntity);
}
