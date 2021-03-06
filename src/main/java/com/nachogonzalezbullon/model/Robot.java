package com.nachogonzalezbullon.model;

import com.nachogonzalezbullon.exceptions.PositionInitializationException;
import com.nachogonzalezbullon.exceptions.RobotInitializationException;

/**
 * The robot in the simulation represented by a {@link Position}, a {@link Orientation} and a life status.
 *
 * @author nachoglezbul on 17/10/2019
 */
public class Robot {

    private static final String LOST_MESSAGE = "LOST";
    private Position position;

    private Orientation orientation;

    private boolean alive;

    /**
     * Creates a new robot.
     *
     * @param position    the {@link Position} of the robot.
     * @param orientation the {@link Orientation} of the robot.
     * @throws RobotInitializationException if the position or the orientation are not provided.
     */
    public Robot(Position position, Orientation orientation) throws RobotInitializationException {
        if (position == null) {
            throw new RobotInitializationException("A robot needs an initial position");
        }

        if (orientation == null) {
            throw new RobotInitializationException("A robot needs an initial orientation");
        }

        this.position = position;
        this.orientation = orientation;
        this.alive = true;
    }

    /**
     * Accomplishes the given instruction.
     *
     * @param instruction The instruction to be accomplished.
     * @throws PositionInitializationException if the robot goes off the edge of the planet.
     */
    public void obey(Instruction instruction) throws PositionInitializationException {
        switch (instruction) {
            case FORWARD:
                this.position = moveForward();
                break;
            case LEFT:
                this.orientation = this.orientation.rotate(false);
                break;
            case RIGHT:
                this.orientation = this.orientation.rotate(true);
                break;
        }
    }

    /**
     * Moves forward according to the current orientation.
     * @return the new {@link Position}
     * @throws PositionInitializationException if the robot goes off the edge of the planet.
     */
    private Position moveForward() throws PositionInitializationException {
        switch (this.orientation) {
            case NORTH:
                return new Position(this.position.getX(), this.position.getY() + 1);
            case EAST:
                return new Position(this.position.getX() + 1, this.position.getY());
            case SOUTH:
                return new Position(this.position.getX(), this.position.getY() - 1);
            case WEST:
                return new Position(this.position.getX() - 1, this.position.getY());
            default:
                return this.position;
        }
    }

    /**
     * Sets the life status to false.
     */
    public void die() {
        this.alive = false;
    }

    /**
     * Moves to the given position.
     * @param position the {@link Position} where the robot has to go.
     */
    public void moveTo(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public boolean isAlive() {
        return alive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Robot robot = (Robot) o;

        if (!position.equals(robot.position)) return false;
        return orientation == robot.orientation;
    }

    @Override
    public int hashCode() {
        int result = position.hashCode();
        result = 31 * result + orientation.hashCode();
        return result;
    }

    @Override
    public String toString() {
        String robotSituation = position + " " + orientation.getAlias();

        if (!alive) {
            robotSituation += " " + LOST_MESSAGE;
        }

        return robotSituation;
    }
}
