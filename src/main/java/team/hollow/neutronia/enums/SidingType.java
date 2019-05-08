package team.hollow.neutronia.enums;

import net.minecraft.util.SnakeCaseIdentifiable;

public enum SidingType implements SnakeCaseIdentifiable {
    SINGLE("single"),
    DOUBLE("double");

    private final String name;

    SidingType(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public String toSnakeCase() {
        return this.name;
    }
}