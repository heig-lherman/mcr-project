package heig.mcr.visitor.board;

import heig.mcr.visitor.handler.InteractionVisitor;

public interface Interactable {

    void accept(InteractionVisitor v);
}
