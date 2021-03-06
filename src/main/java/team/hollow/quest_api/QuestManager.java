package team.hollow.quest_api;

import team.hollow.quest_api.api.Quest;

import java.util.ArrayList;
import java.util.List;

public class QuestManager {

    private static List<Quest[]> quests = new ArrayList<>();

    public static void registerQuests(Quest... quest) {
        quests.add(quest);
    }

    public static List<Quest[]> getQuests() {
        return quests;
    }

}