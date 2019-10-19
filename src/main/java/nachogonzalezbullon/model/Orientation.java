package nachogonzalezbullon.model;

import java.util.HashMap;
import java.util.Map;

public enum Orientation {
    NORTH("N"),
    EAST("E"),
    SOUTH("S"),
    WEST("W");

    private String alias;

    private static final Map<String, Orientation> BY_ALIAS = new HashMap<>();

    static {
        for (Orientation orientation : values()) {
            BY_ALIAS.put(orientation.alias, orientation);
        }
    }

    Orientation(String alias) {
        this.alias = alias;
    }

    public static Orientation valueOfAlias(String alias) {
        return BY_ALIAS.get(alias);
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
