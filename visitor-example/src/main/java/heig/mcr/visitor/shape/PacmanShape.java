package heig.mcr.visitor.shape;

import heig.mcr.visitor.math.DiscreteCoordinates;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;

public class PacmanShape implements MovableShape {

    @Override
    public Shape atPosition(DiscreteCoordinates coords) {
        return new Arc2D.Double(coords.x() * SQUARE_SIZE, coords.y() * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE, 45, 270, Arc2D.PIE);
    }
}
