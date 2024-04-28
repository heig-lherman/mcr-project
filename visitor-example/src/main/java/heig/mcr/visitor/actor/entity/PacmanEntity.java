package heig.mcr.visitor.actor.entity;

import heig.mcr.visitor.actor.Entity;
import heig.mcr.visitor.handler.InteractionVisitor;
import heig.mcr.visitor.math.DiscreteCoordinates;
import heig.mcr.visitor.shape.CircleShape;
import heig.mcr.visitor.shape.PacmanShape;

import java.awt.*;

public class PacmanEntity extends Entity {

    public PacmanEntity() {
        super(new PacmanShape(), Color.ORANGE, new DiscreteCoordinates(5, 16));
    }

    @Override
    public void acceptInteraction(InteractionVisitor v) {
        v.interactWith(this);
    }
}
