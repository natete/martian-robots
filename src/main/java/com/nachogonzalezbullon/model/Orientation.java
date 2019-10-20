package com.nachogonzalezbullon.model;

import java.util.HashMap;
import java.util.Map;

/**
 * The valid orientations that a robot might have.
 */
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

    public String getAlias() {
        return alias;
    }

    public static Orientation valueOfAlias(String alias) {
        return BY_ALIAS.get(alias);
    }

    /**
     * Returns the orientation resulting of rotating the current orientation clockwise or counter clockwise.
     *
     * @param clockwise if the rotation is clockwise or counter clockwise
     * @return the orientation resulting of the rotation
     */
    public Orientation rotate(boolean clockwise) {
        final Orientation[] values = Orientation.values();

        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(this)) {
                // Return the next or the previous depending on if it clockwise or counter clockwise.
                // We keep the values from 0 to 3 using the module to keep a valid value.
                return clockwise ? values[(i + 1) % 4] : values[(i - 1 + 4) % 4];
            }
        }

        return this;
    }
}
