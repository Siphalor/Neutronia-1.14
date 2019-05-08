package team.hollow.neutronia.enums;

import net.minecraft.util.SnakeCaseIdentifiable;

public enum CarvedFaceTypes implements SnakeCaseIdentifiable {
    CREEPER, DERP, DINNER, DUMB, FURNACE, GHAST, GRUMP, GUARDIAN, LANTERN1, LANTERN2, LAUGH, LIVID, NOSE, OBSERVER,
    SAD, SCARED, SMILE, SMIRK, SORROW, SPIDER, SPOOK, STEVE, SURPRISED, WINK;

    @Override
    public String toSnakeCase() {
        return this.toString().toLowerCase();
    }

}