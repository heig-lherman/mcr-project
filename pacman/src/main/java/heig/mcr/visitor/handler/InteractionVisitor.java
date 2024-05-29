package heig.mcr.visitor.handler;

import heig.mcr.visitor.game.actor.Player;
import heig.mcr.visitor.actor.entity.CircleEntity;
import heig.mcr.visitor.actor.entity.PacmanEntity;
import heig.mcr.visitor.actor.entity.TriangleEntity;

public interface InteractionVisitor {

    void interactWith(Player player);

    void interactWith(CircleEntity circleEntity);

    void interactWith(TriangleEntity triangleEntity);

    void interactWith(PacmanEntity pacmanEntity);
}
