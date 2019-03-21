package team.hollow.neutronia.utils.registry;

import net.fabricmc.fabric.api.entity.EntityTrackingRegistry;
import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.hollow.neutronia.Neutronia;

public class EntityRegistryBuilder {

    public static final EntityRegistryBuilder INSTANCE = new EntityRegistryBuilder();
    private static String name;
    private static EntityType.class_4049 builder;

    private EntityCategory category;

    private int trackingDistance;
    private int updateIntervalTicks;
    private boolean alwaysUpdateVelocity;

    private int primaryColor;
    private int secondaryColor;

    private EntitySize size;

    public static EntityRegistryBuilder createBuilder(String nameIn, EntityType.class_4049 builderIn) {
        name = nameIn;
        builder = builderIn;
        return INSTANCE;
    }

    public EntityRegistryBuilder category(EntityCategory category) {
        this.category = category;
        return this;
    }

    public EntityRegistryBuilder tracker(int trackingDistance, int updateIntervalTicks, boolean alwaysUpdateVelocity) {
        this.trackingDistance = trackingDistance;
        this.updateIntervalTicks = updateIntervalTicks;
        this.alwaysUpdateVelocity = alwaysUpdateVelocity;
        return this;
    }

    public EntityRegistryBuilder egg(int primaryColor, int secondaryColor) {
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        return this;
    }

    public EntityRegistryBuilder size(EntitySize size) {
        this.size = size;
        return this;
    }

    public EntityType build() {
        FabricEntityTypeBuilder entityBuilder = FabricEntityTypeBuilder.create(EntityCategory.CREATURE, builder).size(size).disableSaving();
        EntityType entityType = Registry.register(Registry.ENTITY_TYPE, new Identifier(Neutronia.MOD_ID, name), entityBuilder.build());
        if((this.alwaysUpdateVelocity)) {
            if (this.updateIntervalTicks != 0 & this.trackingDistance != 0)
            EntityTrackingRegistry.INSTANCE.register(entityType, trackingDistance, updateIntervalTicks, alwaysUpdateVelocity);
        }
        RegistryUtils.registerItem(new SpawnEggItem(entityType, primaryColor, secondaryColor, new Item.Settings().itemGroup(ItemGroup.MISC)), String.format("%s_spawn_egg", name));
        return entityType;
    }

}