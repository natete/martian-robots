package com.nachogonzalezbullon.model;

import com.nachogonzalezbullon.exceptions.PlanetInitializationException;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author nachoglezbul on 17/10/2019
 */
public class Planet {

    private final Position finalPosition;
    private final Collection<Position> scentPositions;

    public Planet(Position finalPosition) throws PlanetInitializationException {
        if (finalPosition.getX() > 0 && finalPosition.getY() > 0) {
            this.finalPosition = finalPosition;
            this.scentPositions = new HashSet<>();
        } else {
            throw new PlanetInitializationException("A planet has to be two dimensional");
        }
    }

    public void addScentPosition(Position position) {
        this.scentPositions.add(position);
    }

    public boolean isScentPosition(Position position) {
        return scentPositions.contains(position);
    }

    public boolean isInbounds(Position position) {
        return isInAxisBounds(finalPosition.getX(), position.getX()) && isInAxisBounds(finalPosition.getY(), position.getY());
    }

    private boolean isInAxisBounds(final int limit, final int position) {
        return position >= 0 && position <= limit;
    }
}
