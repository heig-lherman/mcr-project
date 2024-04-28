package heig.mcr.visitor.shape;

import heig.mcr.visitor.math.DiscreteCoordinates;

import java.awt.*;

public class SquareShape implements MovableShape {

    @Override
    public Shape atPosition(DiscreteCoordinates coords) {
        return new Rectangle(coords.x() * SQUARE_SIZE, coords.y() * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
    }
}
