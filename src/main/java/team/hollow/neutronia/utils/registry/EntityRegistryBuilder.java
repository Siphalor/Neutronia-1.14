package team.hollow.neutronia.utils.registry;

import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.hollow.neutronia.Neutronia;

public class EntityRegistryBuilder<E extends Entity> {

    private static String name;

    private EntityType.EntityFactory<E> class_4049;

    private EntityCategory category;

    private int trackingDistance;
    private int updateIntervalTicks;
    private boolean alwaysUpdateVelocity;

    private int primaryColor;
    private int secondaryColor;

    private EntitySize size;

    public static <E extends Entity> EntityRegistryBuilder<E> createBuilder(String nameIn) {
        name = nameIn;
        return new EntityRegistryBuilder<>();
    }

    public EntityRegistryBuilder<E> entity(EntityType.EntityFactory<E> class_4049) {
        this.class_4049 = class_4049;
        return this;
    }

    public EntityRegistryBuilder<E> category(EntityCategory category) {
        this.category = category;
        return this;
    }

    public EntityRegistryBuilder<E> tracker(int trackingDistance, int updateIntervalTicks, boolean alwaysUpdateVelocity) {
        this.trackingDistance = trackingDistance;
        this.updateIntervalTicks = updateIntervalTicks;
        this.alwaysUpdateVelocity = alwaysUpdateVelocity;
        return this;
    }

    public EntityRegistryBuilder<E> egg(int primaryColor, int secondaryColor) {
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        return this;
    }

    public EntityRegistryBuilder<E> size(EntitySize size) {
        this.size = size;
        return this;
    }

    public EntityType<E> build() {
        EntityType<E> entityBuilder = FabricEntityTypeBuilder.<E>create(category, EntityType::create).size(size).disableSaving().build();
        EntityType<E> entityType = Registry.register(Registry.ENTITY_TYPE, new Identifier(Neutronia.MOD_ID, name), entityBuilder);
        if ((this.alwaysUpdateVelocity)) {
            if (this.updateIntervalTicks != 0 & this.trackingDistance != 0)
                FabricEntityTypeBuilder.create(category, EntityType::create).size(size).trackable(trackingDistance, updateIntervalTicks, alwaysUpdateVelocity).disableSaving().build();
        }
        RegistryUtils.registerItem(new SpawnEggItem(entityType, primaryColor, secondaryColor, new Item.Settings().itemGroup(ItemGroup.MISC)), String.format("%s_spawn_egg", name));
        return entityType;
    }

}