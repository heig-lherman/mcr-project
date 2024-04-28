package heig.mcr.visitor.handler.support;

import heig.mcr.visitor.actor.Player;
import heig.mcr.visitor.actor.entity.CircleEntity;
import heig.mcr.visitor.actor.entity.PacmanEntity;
import heig.mcr.visitor.actor.entity.TriangleEntity;
import heig.mcr.visitor.handler.InteractionVisitor;

public abstract class AbstractInteractionVisitor implements InteractionVisitor {

    @Override
    public void interactWith(Player player) {
    }

    @Override
    public void interactWith(CircleEntity circleEntity) {
    }

    @Override
    public void interactWith(TriangleEntity triangleEntity) {
    }

    @Override
    public void interactWith(PacmanEntity pacmanEntity) {
    }
}
