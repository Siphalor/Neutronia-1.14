package team.abnormals.neutronia.blocks.pumpkin;

import net.minecraft.util.StringRepresentable;

public class JackOLanternHelper {

    public enum FaceTypes implements StringRepresentable {
        CREEPER, DERP, DINNER, DUMB, FURNACE, GHAST, GRUMP, GUARDIAN, LANTERN1, LANTERN2, LAUGH, LIVID, NOSE, OBSERVER,
        OR, SAD, SCARED, SMILE, SMIRK, SORROW, SPIDER, SPOOK, STEVE, SURPRISED, WINK;

        @Override
        public String asString() {
            return this.toString().toLowerCase();
        }

    }

}
