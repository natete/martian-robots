package com.nachogonzalezbullon.model;

import com.nachogonzalezbullon.exceptions.PositionInitializationException;

/**
 * A position in the simulator, represented by two coordinates, x and y.
 */
public class Position {

    private static final int COORDINATE_MAX_VALUE = 50;

    private final int x;

    private final int y;

    /**
     * Creates a position
     *
     * @param x the x coordinate.
     * @param y the y coordinate.
     * @throws PositionInitializationException if the value is greater than {@link this#COORDINATE_MAX_VALUE}.
     */
    public Position(int x, int y) throws PositionInitializationException {
        if (x > COORDINATE_MAX_VALUE || y > COORDINATE_MAX_VALUE) {
            throw new PositionInitializationException("The maximum value for any coordinate is " + COORDINATE_MAX_VALUE);
        }

        this.x = x;
        this.y = y;
    }

    public Position(Position position) {
        this.x = position.getX();
        this.y = position.getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        return y == position.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}
