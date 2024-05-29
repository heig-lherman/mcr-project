package heig.mcr.visitor.math;

public enum Direction {
    UP(new Vector(0.0f, 1.0f)),
    RIGHT(new Vector(1.0f, 0.0f)),
    DOWN(new Vector(0.0f, -1.0f)),
    LEFT(new Vector(-1.0f, 0.0f));

    // Direction of the Direction
    private final Vector direction;

    Direction(Vector direction) {
        this.direction = direction;
    }

    /**
     * Return the opposite Direction
     *
     * @return the opposite orientation Down:Up, Right:Left
     */
    public Direction opposite() {
        return Direction.values()[(ordinal() + 2) % 4];
    }

    /**
     * Convert an orientation into vector
     *
     * @return the vector
     */
    public Vector toVector() {
        return direction;
    }

    /**
     * Convert an int into an orientation
     *
     * @param index the int representing the orientation
     * @return the orientation
     */
    public static Direction fromInt(int index) {
        switch (index) {
            case 0:
                return Direction.UP;
            case 1:
                return Direction.RIGHT;
            case 2:
                return Direction.DOWN;
            case 3:
                return Direction.LEFT;
        }
        return null;
    }

    /**
     * Convert a vector into an orientation
     *
     * @param v the vector representing the orientation
     * @return the orientation
     */
    public static Direction fromVector(Vector v) {
        if (v.x() > 0 && v.y() == 0)
            return Direction.RIGHT;
        if (v.x() < 0 && v.y() == 0)
            return Direction.LEFT;
        if (v.y() > 0 && v.x() == 0)
            return Direction.UP;
        if (v.y() < 0 && v.x() == 0)
            return Direction.DOWN;
        return null;
    }

    @Override
    public String toString() {
        return super.toString() + direction.toString();
    }
}
