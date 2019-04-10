package team.hollow.neutronia.social_villager;

public class VillagerData {
    private static final int[] LEVEL_BASE_EXPERIENCE = new int[]{0, 10, 50, 100, 150};
    private final VillagerProfession profession;
    private final int level;

    public VillagerData(VillagerProfession villagerProfession_1, int int_1) {
        this.profession = villagerProfession_1;
        this.level = Math.max(1, int_1);
    }

    public static int getLowerLevelExperience(int int_1) {
        return canLevelUp(int_1) ? LEVEL_BASE_EXPERIENCE[int_1 - 1] : 0;
    }

    public static int getUpperLevelExperience(int int_1) {
        return canLevelUp(int_1) ? LEVEL_BASE_EXPERIENCE[int_1] : 0;
    }

    public static boolean canLevelUp(int int_1) {
        return int_1 >= 1 && int_1 < 5;
    }

    public VillagerProfession getProfession() {
        return this.profession;
    }

    public int getLevel() {
        return this.level;
    }

    public VillagerData withLevel(int int_1) {
        return new VillagerData(this.profession, int_1);
    }
}