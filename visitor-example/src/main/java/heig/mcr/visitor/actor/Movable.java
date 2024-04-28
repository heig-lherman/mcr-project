package heig.mcr.visitor.actor;

import heig.mcr.visitor.math.DiscreteCoordinates;

public interface Movable extends Positionable {

    void moveTo(DiscreteCoordinates coordinates);
}
