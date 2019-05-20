package team.hollow.neutronia.world.biome.wip;

import net.minecraft.block.BlockSand;
import net.minecraft.entity.monster.EntityHusk;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;

public class RedDesertBiome extends Biome {

    public RedDesertBiome() {
        super(new Settings().downfall(0.0F).temperature(2.0F).depth(Biomes.DESERT.getDepth()).scale(Biomes.DESERT.getScale()).waterColor(0xcaba8a));
        this.spawnableCreatureList.clear();
        this.topBlock = Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND);
        this.fillerBlock = Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND);
        this.decorator.treesPerChunk = -999;
        this.decorator.deadBushPerChunk = 2;
        this.decorator.reedsPerChunk = 50;
        this.decorator.cactiPerChunk = 10;
        this.spawnableCreatureList.clear();
        this.spawnableCreatureList.add(new Biome.SpawnListEntry(EntityRabbit.class, 4, 2, 3));

        this.spawnableMonsterList.removeIf(biome$spawnlistentry -> biome$spawnlistentry.entityClass == EntityZombie.class || biome$spawnlistentry.entityClass == EntityZombieVillager.class);

        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityZombie.class, 19, 4, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityZombieVillager.class, 1, 1, 1));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityHusk.class, 80, 4, 4));
    }

}
