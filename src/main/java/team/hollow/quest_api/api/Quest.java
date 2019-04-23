package team.hollow.quest_api.api;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class Quest {

    public Identifier name;
    public ItemStack icon;
    public QuestTask task;

    public Quest(Identifier name, ItemStack icon, QuestTask task) {
        this.name = name;
        this.icon = icon;
        this.task = task;
    }

    public Identifier getName() {
        return name;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public QuestTask getTask() {
        return task;
    }

}