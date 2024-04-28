package heig.mcr.visitor.actor.entity;

import heig.mcr.visitor.actor.Entity;
import heig.mcr.visitor.handler.InteractionVisitor;
import heig.mcr.visitor.math.DiscreteCoordinates;
import heig.mcr.visitor.shape.CircleShape;

import java.awt.*;

public class CircleEntity extends Entity {

    public CircleEntity() {
        super(new CircleShape(), Color.RED, new DiscreteCoordinates(12, 8));
    }

    @Override
    public void acceptInteraction(InteractionVisitor v) {
        v.interactWith(this);
    }
}
