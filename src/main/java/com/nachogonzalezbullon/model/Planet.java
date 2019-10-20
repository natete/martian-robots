package com.nachogonzalezbullon.model;

import com.nachogonzalezbullon.exceptions.PlanetInitializationException;

import java.util.Collection;
import java.util.HashSet;

/**
 * The planet where the simulation takes place.
 *
 * @author nachoglezbul on 17/10/2019
 */
public class Planet {

    /**
     * The {@link Position} of the upper-right corner of the planet.
     */
    private final Position finalPosition;

    /**
     * The collection of {@link Position} from where a robot went off the edge.
     */
    private final Collection<Position> scentPositions;

    /**
     * A planet has to be a two dimensional grid, so both coordinates of the upper-right corner must be bigger than 0.
     *
     * @param finalPosition the {@link Position} that represents the upper-right corner.
     * @throws PlanetInitializationException if one of the coordinates is 0 or less.
     */
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

    /**
     * Tells if a given {@link Position} is in the planet or off the edges.
     *
     * @param position the {@link Position} to be checked.
     * @return true if the {@link Position} is in the planet, false otherwise.
     */
    public boolean isInbounds(Position position) {
        return isInAxisBounds(finalPosition.getX(), position.getX()) && isInAxisBounds(finalPosition.getY(), position.getY());
    }

    /**
     * Checks if a given coordinate is in the limits of the given axis.
     *
     * @param axisLimit  the given axis limit.
     * @param coordinate the given coordinate.
     * @return true if the coordinate is valid, false otherwise.
     */
    private boolean isInAxisBounds(final int axisLimit, final int coordinate) {
        return coordinate >= 0 && coordinate <= axisLimit;
    }
}
