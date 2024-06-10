package heig.mcr.visitor.math;

import heig.mcr.visitor.board.Cell;
import heig.mcr.visitor.board.Entity;
import java.util.*;

public final class Pathfinding {

    /**
     * Find the nearest entity of a given class from a starting cell, using a breadth-first search.
     *
     * @param entityClass The class of the entity to find
     * @param start       The starting cell
     * @return The nearest entity of the given class, or null if none was found
     */
    public static <T extends Entity> T findNearestEntity(
            Class<T> entityClass,
            Cell start
    ) {
        Queue<Cell> queue = new LinkedList<>();
        Set<Cell> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            Cell current = queue.poll();

            Entity entity = current.getOccupants().stream()
                    .filter(entityClass::isInstance)
                    .findFirst().orElse(null);
            if (Objects.nonNull(entity)) {
                return entityClass.cast(entity);
            }

            for (Direction direction : Direction.values()) {
                Cell neighbor = current.getNeighbor(direction);
                if (Objects.nonNull(neighbor) && !visited.contains(neighbor)) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                }
            }
        }

        return null;
    }

    /**
     * Find the shortest path between two cells, using a breadth-first search.
     *
     * @param start  The starting cell
     * @param target The target cell
     * @return The list of directions to follow to reach the target cell, or an empty list if no path was found
     */
    public static List<Direction> findShortestPath(Cell start, Cell target, Entity entity) {
        if (start.equals(target)) {
            return Collections.emptyList();
        }

        Queue<Node> queue = new LinkedList<>();
        Set<Cell> visited = new HashSet<>();

        queue.add(new Node(null, start, null));
        visited.add(start);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            Cell cell = current.cell;

            if (cell.equals(target)) {
                return current.getPath();
            }

            for (Direction direction : Direction.values()) {
                Cell neighbor = cell.getNeighbor(direction);
                if (Objects.nonNull(neighbor) && !visited.contains(neighbor) && neighbor.isWalkableBy(entity)) {
                    queue.add(new Node(direction, neighbor, current));
                    visited.add(neighbor);
                }
            }
        }

        return Collections.emptyList();
    }

    private static class Node {
        Direction direction;
        Cell cell;
        Node parent;

        Node(Direction direction, Cell cell, Node parent) {
            this.direction = direction;
            this.cell = cell;
            this.parent = parent;
        }

        List<Direction> getPath() {
            if (parent == null) {
                return new LinkedList<>();
            }

            List<Direction> path = parent.getPath();
            path.add(direction);
            return path;
        }
    }
}
