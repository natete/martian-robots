package com.nachogonzalezbullon.dto;

/**
 * Class that represents a Data Transfer Object for the {@link com.nachogonzalezbullon.model.Position} class.
 *
 * @author nachoglezbul on 19/10/2019
 */
public class PositionDTO {

    private final int x;
    private final int y;

    public PositionDTO(int x, int y) {
        this.x = x;
        this.y = y;
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

        PositionDTO that = (PositionDTO) o;

        if (x != that.x) return false;
        return y == that.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
