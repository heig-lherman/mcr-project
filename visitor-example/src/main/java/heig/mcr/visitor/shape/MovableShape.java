package heig.mcr.visitor.shape;

import heig.mcr.visitor.math.DiscreteCoordinates;

import java.awt.*;

public interface MovableShape {

    int SQUARE_SIZE = 32;

    Shape atPosition(DiscreteCoordinates coords);
}
