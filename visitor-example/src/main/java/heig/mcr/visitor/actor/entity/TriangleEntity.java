package heig.mcr.visitor.actor.entity;

import heig.mcr.visitor.actor.Entity;
import heig.mcr.visitor.handler.InteractionVisitor;
import heig.mcr.visitor.math.DiscreteCoordinates;
import heig.mcr.visitor.shape.TriangleShape;

import java.awt.*;

public class TriangleEntity extends Entity {

    public TriangleEntity() {
        super(new TriangleShape(), Color.GREEN, new DiscreteCoordinates(4, 3));
    }

    @Override
    public void acceptInteraction(InteractionVisitor v) {
        v.interactWith(this);
    }
}
