package nachogonzalezbullon.model;

public enum Instruction {
    LEFT("L"),
    RIGHT("R"),
    FORWARD("F");

    private String alias;

    Instruction(String alias) {
        this.alias = alias;
    }
}
