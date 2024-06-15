package heig.mcr.visitor.handler;

import heig.mcr.visitor.game.actor.Pellet;
import heig.mcr.visitor.game.actor.Player;
import heig.mcr.visitor.game.actor.SuperPellet;
import heig.mcr.visitor.game.actor.npc.*;

/**
 * Visitor pattern for interactions between actors.
 *
 * @author Loïc Herman
 * @author Massimo Stefani
 * @author Samuel Roland
 * @author Timothée Van Hove
 */
public interface InteractionVisitor {

    void visit(Player player);

    // Pellets
    void visit(Pellet pellet);

    void visit(SuperPellet pellet);

    // Ghosts
    void visit(Vader vader);

    void visit(Luke luke);

    void visit(Sith sith);

    void visit(StormTrooper stormTrooper);

    void visit(BobaFett bobaFett);
}
