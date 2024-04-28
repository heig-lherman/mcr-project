package heig.mcr.visitor.actor;

import heig.mcr.visitor.math.DiscreteCoordinates;
import heig.mcr.visitor.shape.MovableShape;

import java.awt.*;

public abstract class Entity implements Interactable, Drawable, Positionable {

    private MovableShape shape;
    private Color color;
    private DiscreteCoordinates coordinates;

    protected Entity(
            MovableShape shape,
            Color color,
            DiscreteCoordinates coordinates
    ) {
        this.shape = shape;
        this.color = color;
        this.coordinates = coordinates;
    }

    public MovableShape getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public DiscreteCoordinates getCoordinates() {
        return coordinates;
    }

    protected void setCoordinates(DiscreteCoordinates coordinates) {
        this.coordinates = coordinates;
    }

    protected void setColor(Color color) {
        this.color = color;
    }

    protected void setShape(MovableShape shape) {
        this.shape = shape;
    }

    @Override
    public void draw(Graphics2D canvas) {
        canvas.setColor(color);
        canvas.fill(shape.atPosition(coordinates));
    }
}
