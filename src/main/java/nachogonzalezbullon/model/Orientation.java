package nachogonzalezbullon.model;

public enum Orientation {
    NORTH("N"),
    EAST("E"),
    SOUTH("S"),
    WEST("W");

    private String alias;

    Orientation(String alias) {
        this.alias = alias;
    }

    public Orientation rotate(boolean clockwise) {
        final Orientation[] values = Orientation.values();

        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(this)) {
                return clockwise ? values[(i + 1) % 4] : values[(i - 1 + 4) % 4];
            }
        }

        return this;
    }
}
