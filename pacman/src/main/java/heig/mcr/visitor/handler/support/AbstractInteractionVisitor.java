package heig.mcr.visitor.handler.support;

import heig.mcr.visitor.game.actor.Pellet;
import heig.mcr.visitor.game.actor.Player;
import heig.mcr.visitor.game.actor.SuperPellet;
import heig.mcr.visitor.game.actor.npc.*;
import heig.mcr.visitor.handler.InteractionVisitor;

/**
 * Abstract implementation of the InteractionVisitor interface, providing empty implementations for all methods.
 * This allows to only override the methods that are needed.
 *
 * @author Loïc Herman
 * @author Massimo Stefani
 * @author Samuel Roland
 * @author Timothée Van Hove
 */
public abstract class AbstractInteractionVisitor implements InteractionVisitor {

    @Override
    public void visit(Player player) {
    }

    @Override
    public void visit(Pellet pellet) {
    }

    @Override
    public void visit(SuperPellet superPellet) {
        this.visit((Pellet) superPellet);
    }

    @Override
    public void visit(Vader vader) {
    }

    @Override
    public void visit(Luke luke) {
    }

    @Override
    public void visit(Sith sith) {
    }

    @Override
    public void visit(StormTrooper stormTrooper) {
    }

    @Override
    public void visit(BobaFett bobaFett) {
    }
}
