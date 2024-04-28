package heig.mcr.visitor.math;

import java.util.List;
import java.util.Objects;

public record DiscreteCoordinates(
        int x,
        int y
) {

    public static DiscreteCoordinates ORIGIN = new DiscreteCoordinates(0, 0);

    public DiscreteCoordinates left() {
        return new DiscreteCoordinates(x - 1, y);
    }

    public DiscreteCoordinates right() {
        return new DiscreteCoordinates(x + 1, y);
    }

    public DiscreteCoordinates up() {
        return new DiscreteCoordinates(x, y - 1);
    }

    public DiscreteCoordinates down() {
        return new DiscreteCoordinates(x, y + 1);
    }

    public List<DiscreteCoordinates> getNeighbours() {
        return List.of(left(), right(), up(), down());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiscreteCoordinates that = (DiscreteCoordinates) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
