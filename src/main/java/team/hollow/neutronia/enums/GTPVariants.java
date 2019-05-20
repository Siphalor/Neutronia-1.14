package team.hollow.neutronia.enums;

import net.minecraft.util.StringIdentifiable;

public enum GTPVariants implements StringIdentifiable {

    WHITE(0, "white"),
    ORANGE(1, "orange"),
    MAGENTA(2, "magenta"),
    LIGHT_BLUE(3, "light_blue"),
    YELLOW(4, "yellow"),
    LIME(5, "lime"),
    PINK(6, "pink"),
    GRAY(7, "gray"),
    LIGHT_GRAY(8, "light_gray"),
    CYAN(9, "cyan"),
    PURPLE(10, "purple"),
    BLUE(11, "blue"),
    BROWN(12, "brown"),
    GREEN(13, "green"),
    RED(14, "red"),
    BLACK(15, "black");

    private String name;
    private int id;

    GTPVariants(int id, String name) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String asString() {
        return this.name;
    }

    public int getId() {
        return id;
    }

}