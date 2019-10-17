package nachogonzalezbullon.model;

/**
 * @author nachoglezbul on 17/10/2019
 */
public class Robot {

    private Position position;

    private Orientation orientation;

    public Robot(Position position, Orientation orientation) {
        if (position == null) {
            throw new ExceptionInInitializerError("A robot needs an initial position");
        }

        if (orientation == null) {
            throw new ExceptionInInitializerError("A robot needs an initial orientation");
        }

        this.position = position;
        this.orientation = orientation;
    }

    public void obey(Instruction instruction) {
        switch (instruction) {
            case FORWARD:
                this.position = getNewPosition();
                break;
            case LEFT:
                this.orientation = this.orientation.rotate(false);
                break;
            case RIGHT:
                this.orientation = this.orientation.rotate(true);
                break;
        }
    }

    private Position getNewPosition() {
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
}
