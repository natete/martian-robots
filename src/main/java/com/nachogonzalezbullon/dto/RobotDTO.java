package com.nachogonzalezbullon.dto;

import com.nachogonzalezbullon.model.Orientation;

/**
 * @author nachoglezbul on 19/10/2019
 */
public class RobotDTO {
    private final int x;
    private final int y;
    private final Orientation orientation;

    public RobotDTO(int x, int y, Orientation orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RobotDTO robotDTO = (RobotDTO) o;

        if (x != robotDTO.x) return false;
        if (y != robotDTO.y) return false;
        return orientation == robotDTO.orientation;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        result = 31 * result + orientation.hashCode();
        return result;
    }
}
