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
        List<Entity> sortedOccupants = new ArrayList<>(occupants);
        sortedOccupants.sort(Comparator.comparingInt(Entity::getLayer));
        return sortedOccupants;
    }

    public void addOccupant(Entity entity) {
        occupants.add(entity);
    }

    public void removeOccupant(Entity entity) {
        occupants.remove(entity);
    }

    public abstract boolean isWalkableBy(Entity entity);

    public abstract Sprite getSprite();
}
