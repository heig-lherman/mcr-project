package heig.mcr.visitor.math;

import heig.mcr.visitor.board.Cell;
import heig.mcr.visitor.board.Entity;

import java.util.*;

public final class Pathfinding {

    /**
     * Find the nearest entity of a given class from a starting cell, using a breadth-first search.
     * @param entityClass The class of the entity to find
     * @param start The starting cell
     * @return The nearest entity of the given class, or null if none was found
     */
    public static <T extends Entity> T findNearestEntity(
            Class<T> entityClass,
            Cell start
    ) {
        List<Cell> queue = new LinkedList<>();
        Set<Cell> visited = new HashSet<>();

        queue.add(start);

        while (!queue.isEmpty()) {
            Cell current = queue.remove(0);

            Entity entity = current.getOccupants().stream()
                    .filter(entityClass::isInstance)
                    .findFirst().orElse(null);
            if (Objects.nonNull(entity)) {
                return entityClass.cast(entity);
            }

            visited.add(current);

            for (Direction direction : Direction.values()) {
                Cell neighbor = current.getNeighbor(direction);
                if (Objects.nonNull(neighbor) && !visited.contains(neighbor)) {
                    queue.add(neighbor);
                }
            }
        }

        return null;
    }

    /**
     * Find the shortest path between two cells, using a breadth-first search.
     * @param start The starting cell
     * @param target The target cell
     * @return The list of directions to follow to reach the target cell, or an empty list if no path was found
     */
    public static List<Direction> findShortestPath(Cell start, Cell target) {
        if (start.equals(target)) {
            return Collections.emptyList();
        }

        List<Node> queue = new LinkedList<>();
        Set<Cell> visited = new HashSet<>();

        queue.add(new Node(null, start, null));

        while (!queue.isEmpty()) {
            Node current = queue.remove(0);
            Cell cell = current.cell;

            if (cell.equals(target)) {
                return current.getPath();
            }

            visited.add(cell);

            for (Direction direction : Direction.values()) {
                Cell neighbor = cell.getNeighbor(direction);
                if (Objects.nonNull(neighbor) && !visited.contains(neighbor) && neighbor.isWalkable()) {
                    queue.add(new Node(direction, neighbor, current));
                }
            }
        }

        return null;
    }

    private record Node(
            Direction direction,
            Cell cell,
            Node parent
    ) {

        public List<Direction> getPath() {
            if (parent == null) {
                return new LinkedList<>();
            }

            List<Direction> path = parent.getPath();
            path.add(direction);
            return path;
        }
    }
}
