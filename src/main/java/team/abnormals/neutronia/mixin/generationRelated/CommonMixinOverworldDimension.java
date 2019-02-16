/*
 * Copyright (c) 2015-2019, Terrence Ezrol (ezterry)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * + Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * + Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

package team.abnormals.neutronia.mixin.generationRelated;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.JsonOps;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.datafixers.NbtOps;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.source.*;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.dimension.OverworldDimension;
import net.minecraft.world.gen.chunk.ChunkGeneratorType;
import net.minecraft.world.gen.chunk.OverworldChunkGeneratorConfig;
import net.minecraft.world.level.LevelGeneratorType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import team.abnormals.neutronia.Neutronia;
import team.abnormals.neutronia.api.RegionCore;
import team.abnormals.neutronia.world.ImprovedOverworldBiomeSource;
import team.abnormals.neutronia.world.ImprovedOverworldChunkGenerator;
import team.abnormals.neutronia.world.ImprovedOverworldChunkGeneratorConfig;
import team.abnormals.neutronia.world.OverworldChunkGenerator;

@Mixin(OverworldDimension.class)
public abstract class CommonMixinOverworldDimension extends Dimension {
    public CommonMixinOverworldDimension(World world_1, DimensionType dimensionType_1) {
        super(world_1, dimensionType_1);
    }

    private static final Logger LOGGER = LogManager.getLogger("ChunkGenType");

    @Inject(at = @At(value="INVOKE",
            target="Lnet/minecraft/world/gen/chunk/ChunkGeneratorType;createSettings()Lnet/minecraft/world/gen/chunk/ChunkGeneratorConfig;"), method = "createChunkGenerator", cancellable = true)
    public void createChunkGenerator(CallbackInfoReturnable ci){
        LevelGeneratorType type = this.world.getLevelProperties().getGeneratorType();
        ChunkGeneratorType<team.abnormals.neutronia.world.OverworldChunkGeneratorConfig, OverworldChunkGenerator> IMPROVED_OVERWORLD = Neutronia.IMPROVED_OVERWORLD;
        ChunkGeneratorType<ImprovedOverworldChunkGeneratorConfig, ImprovedOverworldChunkGenerator> IMPROVED_OVERWORLD_TWO = Neutronia.IMPROVED_OVERWORLD_TWO;

        if(type == Neutronia.IMPROVED_OVERWORLD_LEVEL_TYPE){
            LOGGER.info("Use wastelands generator");
            team.abnormals.neutronia.world.OverworldChunkGeneratorConfig settings = new team.abnormals.neutronia.world.OverworldChunkGeneratorConfig();
            VanillaLayeredBiomeSourceConfig biomeSrcCfg = BiomeSourceType.VANILLA_LAYERED.getConfig().setGeneratorSettings(new OverworldChunkGeneratorConfig()).setLevelProperties(this.world.getLevelProperties());

            //noinspection unchecked
            ci.setReturnValue(
                    IMPROVED_OVERWORLD.create(this.world, new VanillaLayeredBiomeSource(biomeSrcCfg), settings)
            );
        }
        if(type == Neutronia.IMPROVED_OVERWORLD_TWO_LEVEL_TYPE){
            LOGGER.info("Use wastelands generator");
            ImprovedOverworldChunkGeneratorConfig settings = new ImprovedOverworldChunkGeneratorConfig();
            VanillaLayeredBiomeSourceConfig biomeSrcCfg = BiomeSourceType.VANILLA_LAYERED.getConfig().setGeneratorSettings(new OverworldChunkGeneratorConfig()).setLevelProperties(this.world.getLevelProperties());

            //noinspection unchecked
            ci.setReturnValue(
                    IMPROVED_OVERWORLD_TWO.create(this.world, new ImprovedOverworldBiomeSource(biomeSrcCfg, settings), settings)
            );
        }
        if(type == LevelGeneratorType.BUFFET){
            //likely we don't care, but see if wastelands is selected
            JsonElement rootElement = Dynamic.convert(NbtOps.INSTANCE, JsonOps.INSTANCE, this.world.getLevelProperties().getGeneratorOptions());
            JsonObject rootObj = rootElement.getAsJsonObject();
            String generator = "unknown";
            LOGGER.info("testing:  " + rootObj.toString());
            try {
                generator = rootObj.getAsJsonObject("chunk_generator").getAsJsonPrimitive("type").getAsString();
            } catch (Exception e){
                return;
            }
            if(generator.equals("neutronia:improved_overworld")){
                team.abnormals.neutronia.world.OverworldChunkGeneratorConfig settings = new team.abnormals.neutronia.world.OverworldChunkGeneratorConfig();

                //get the selected biome
                Biome singlebiome = Biomes.DEEP_COLD_OCEAN;
                try{
                    String biomename = rootObj.getAsJsonObject("biome_source")
                            .getAsJsonObject()
                            .getAsJsonObject("options")
                            .getAsJsonArray("biomes")
                            .get(0).getAsString();
                    singlebiome = Registry.BIOME.get(new Identifier(biomename));
                } catch (Exception e){
                    LOGGER.error("Unable to parse biome from Buffet typo");
                }
                FixedBiomeSourceConfig fixedCfg = new FixedBiomeSourceConfig();
                fixedCfg.setBiome(singlebiome);

                //noinspection unchecked
                ci.setReturnValue(
                        IMPROVED_OVERWORLD.create(this.world, new FixedBiomeSource(fixedCfg),settings)
                );
            }
            if(generator.equals("neutronia:improved_overworld_two")){
                //we are up use the "BUFFET" default
                String json = "{\"terrainvariation\":{\"amplification\":30,\"variation\":1},\"randopts\":{\"oceans\":false,\"oceanblock\":\"minecraft:water\",\"globaloffset\":0,\"villages\":false,\"villagechance\":10.0,\"strongholds\":false},\"domes\":{\"lgmincount\":3,\"lgmaxcount\":4,\"lgradius\":34,\"lgheight\":7,\"midmincount\":2,\"midmaxcount\":3,\"midradius\":23,\"midheight\":8,\"smmincount\":1,\"smmaxcount\":2,\"smradius\":18,\"smheight\":9},\"shallows\":{\"mincount\":4,\"maxcount\":5,\"radius\":42,\"depth\":5},\"spire\":{\"count\":2,\"size\":6}}";
                CompoundTag tags = ImprovedOverworldChunkGeneratorConfig.CoreConfigToCompound(
                        new RegionCore(json,null,null));
                ImprovedOverworldChunkGeneratorConfig settings = new ImprovedOverworldChunkGeneratorConfig(tags);
                settings.initBuffet(true);

                //get the selected biome
                Biome singlebiome = Biomes.DEEP_COLD_OCEAN;
                try{
                    String biomename = rootObj.getAsJsonObject("biome_source")
                            .getAsJsonObject()
                            .getAsJsonObject("options")
                            .getAsJsonArray("biomes")
                            .get(0).getAsString();
                    singlebiome = Registry.BIOME.get(new Identifier(biomename));
                } catch (Exception e){
                    LOGGER.error("Unable to parse biome from Buffet typo");
                }
                FixedBiomeSourceConfig fixedCfg = new FixedBiomeSourceConfig();
                fixedCfg.setBiome(singlebiome);

                //noinspection unchecked
                ci.setReturnValue(
                        IMPROVED_OVERWORLD_TWO.create(this.world, new FixedBiomeSource(fixedCfg),settings)
                );
            }
        }
    }

    //custom spawn logic
    @Inject(at = @At("HEAD"), method = "getTopSpawningBlockPosition", cancellable = true)
    public void playerSpawn(int blockx, int blockz, boolean force,CallbackInfoReturnable ci){
        LevelGeneratorType type = this.world.getLevelProperties().getGeneratorType();
        if(type == Neutronia.IMPROVED_OVERWORLD_TWO_LEVEL_TYPE){
            //correct a spawn location
            ImprovedOverworldChunkGenerator gen = (ImprovedOverworldChunkGenerator) world.getChunkManager().getChunkGenerator();
            //noinspection unchecked
            ci.setReturnValue(gen.verifySpawn(blockx, blockz, force));
        }
    }

    @Override
    @Environment(EnvType.CLIENT)
    public float getCloudHeight() {
        LevelGeneratorType type = this.world.getLevelProperties().getGeneratorType();

        if(type == Neutronia.IMPROVED_OVERWORLD_TWO_LEVEL_TYPE){
            return 200.0f;
        }
        return super.getCloudHeight();
    }
}