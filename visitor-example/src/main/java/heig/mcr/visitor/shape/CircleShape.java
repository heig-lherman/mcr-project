package heig.mcr.visitor.shape;

import heig.mcr.visitor.math.DiscreteCoordinates;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class CircleShape implements MovableShape {

    @Override
    public Shape atPosition(DiscreteCoordinates coords) {
        return new Ellipse2D.Double(coords.x() * SQUARE_SIZE, coords.y() * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
    }
}
