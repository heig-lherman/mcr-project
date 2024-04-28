package heig.mcr.visitor.actor;

import heig.mcr.visitor.handler.InteractionVisitor;

public interface Interactable extends Positionable {

    void acceptInteraction(InteractionVisitor v);
}
