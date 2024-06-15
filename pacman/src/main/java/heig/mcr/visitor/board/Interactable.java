package heig.mcr.visitor.board;

import heig.mcr.visitor.handler.InteractionVisitor;

/**
 * An interactable is an entity that can interact with other entities, the base for the visitor pattern.
 *
 * @author Loïc Herman
 * @author Massimo Stefani
 * @author Samuel Roland
 * @author Timothée Van Hove
 */
public interface Interactable {

    void accept(InteractionVisitor v);
}
