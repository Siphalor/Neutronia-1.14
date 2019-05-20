//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package team.hollow.neutronia.quest;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public enum QuestProgress {
    AVAILABLE("available", 0),
    LOCKED("locked", 1),
    SELECTED("selected", 2),
    FINISHED("finished", 3);

    private final String id;
    private final int texV;

    QuestProgress(String string_1, int int_1) {
        this.id = string_1;
        this.texV = int_1;
    }

    public static QuestProgress forName(String string_1) {
        for (QuestProgress questProgress_1 : values()) {
            if (questProgress_1.id.equals(string_1)) return questProgress_1;
        }
        throw new IllegalArgumentException("Unknown frame type '" + string_1 + "'");
    }

    public String getId() {
        return this.id;
    }

    @Environment(EnvType.CLIENT)
    public int texV() {
        return this.texV;
    }

}
