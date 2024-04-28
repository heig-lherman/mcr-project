package heig.mcr.visitor.actor;

import heig.mcr.visitor.GameWindow;
import heig.mcr.visitor.actor.entity.CircleEntity;
import heig.mcr.visitor.actor.entity.PacmanEntity;
import heig.mcr.visitor.actor.entity.TriangleEntity;
import heig.mcr.visitor.handler.InteractionVisitor;
import heig.mcr.visitor.handler.support.AbstractInteractionVisitor;
import heig.mcr.visitor.math.DiscreteCoordinates;
import heig.mcr.visitor.shape.SquareShape;

import java.awt.*;

public class Player extends Entity implements Interactor, Movable {

    private final PlayerInteractionVisitor handler;

    public Player() {
        super(new SquareShape(), Color.BLUE, new DiscreteCoordinates(10, 10));
        handler = new PlayerInteractionVisitor();
    }

    @Override
    public void moveTo(DiscreteCoordinates coordinates) {
        setCoordinates(coordinates);
    }

    @Override
    public void interactWith(Interactable other) {
        other.acceptInteraction(handler);
    }

    @Override
    public void acceptInteraction(InteractionVisitor v) {
        v.interactWith(this);
    }

    private class PlayerInteractionVisitor extends AbstractInteractionVisitor {

        @Override
        public void interactWith(CircleEntity circleEntity) {
            setColor(circleEntity.getColor());
            GameWindow.getInstance().despawn(circleEntity);
        }

        @Override
        public void interactWith(TriangleEntity triangleEntity) {
            setShape(triangleEntity.getShape());
            GameWindow.getInstance().despawn(triangleEntity);
            GameWindow.getInstance().spawn(new PacmanEntity()); // shhh, don't tell anyone
        }

        @Override
        public void interactWith(PacmanEntity pacmanEntity) {
            setColor(pacmanEntity.getColor());
            setShape(pacmanEntity.getShape());
            GameWindow.getInstance().implode();
        }
    }
}
