package heig.mcr.visitor.board;

import heig.mcr.visitor.math.Direction;
import heig.mcr.visitor.window.sprite.Sprite;

import java.util.*;

public abstract class Cell {

    private final Set<Entity> occupants = new HashSet<>();
    private final Map<Direction, Cell> neighbors = new EnumMap<>(Direction.class);

    public Cell getNeighbor(Direction direction) {
        return neighbors.get(direction);
    }

    public void linkNeighbor(Direction direction, Cell cell) {
        neighbors.put(direction, cell);
    }

    public Collection<Entity> getOccupants() {
        return Set.copyOf(occupants);
    }

    public void addOccupant(Entity entity) {
        occupants.add(entity);
    }

    public void removeOccupant(Entity entity) {
        occupants.remove(entity);
    }

    public abstract boolean isWalkable();

    public abstract Sprite getSprite();
}
