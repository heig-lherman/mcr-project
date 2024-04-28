package heig.mcr.visitor.shape;

import heig.mcr.visitor.math.DiscreteCoordinates;

import java.awt.*;

public class TriangleShape implements MovableShape {

    @Override
    public Shape atPosition(DiscreteCoordinates coords) {
        return new Polygon(
                new int[]{coords.x() * SQUARE_SIZE, coords.x() * SQUARE_SIZE + SQUARE_SIZE, coords.x() * SQUARE_SIZE + SQUARE_SIZE / 2},
                new int[]{coords.y() * SQUARE_SIZE + SQUARE_SIZE, coords.y() * SQUARE_SIZE + SQUARE_SIZE, coords.y() * SQUARE_SIZE},
                3
        );
    }
}
