package nachogonzalezbullon.model;

import java.util.HashMap;
import java.util.Map;

public enum Instruction {
    LEFT("L"),
    RIGHT("R"),
    FORWARD("F");

    private String alias;

    private static final Map<String, Instruction> BY_ALIAS = new HashMap<>();

    static {
        for (Instruction instruction : values()) {
            BY_ALIAS.put(instruction.alias, instruction);
        }
    }

    Instruction(String alias) {
        this.alias = alias;
    }

    public static Instruction valueOfAlias(String alias) {
        return BY_ALIAS.get(alias);
    }

}
