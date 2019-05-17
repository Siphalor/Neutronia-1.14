package team.hollow.neutronia.enums;

import net.minecraft.util.StringIdentifiable;

public enum CarvedFaceType implements StringIdentifiable {
    CREEPER, DERP, DINNER, DUMB, FURNACE, GHAST, GRUMP, GUARDIAN, LANTERN1, LANTERN2, LAUGH, LIVID, NOSE, OBSERVER,
    SAD, SCARED, SMILE, SMIRK, SORROW, SPIDER, SPOOK, STEVE, SURPRISED, WINK;

    @Override
    public String asString() {
        return this.toString().toLowerCase();
    }

}