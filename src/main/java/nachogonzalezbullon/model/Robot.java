package nachogonzalezbullon.model;

import nachogonzalezbullon.exceptions.PositionInitializationException;
import nachogonzalezbullon.exceptions.RobotInitializationException;

/**
 * @author nachoglezbul on 17/10/2019
 */
public class Robot {

    private Position position;

    private Orientation orientation;

    public Robot(Position position, Orientation orientation) throws RobotInitializationException {
        if (position == null) {
            throw new RobotInitializationException("A robot needs an initial position");
        }

        if (orientation == null) {
            throw new RobotInitializationException("A robot needs an initial orientation");
        }

        this.position = position;
        this.orientation = orientation;
    }

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

    public Position getPosition() {
        return position;
    }

    public Orientation getOrientation() {
        return orientation;
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
}
