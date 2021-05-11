package com.teamaurora.horizons.common.world.biome;

import com.minecraftabnormals.abnormals_core.core.util.DataUtil;
import com.teamaurora.horizons.core.Horizons;
import com.teamaurora.horizons.core.registry.HorizonsBiomes;
import com.teamaurora.horizons.core.registry.HorizonsFeatures;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Horizons.MODID)
public class HorizonsBiomeFeatures {
    @SubscribeEvent
    public static void addFeatures(BiomeLoadingEvent event) {
        ResourceLocation biomeName = event.getName();

        if (DataUtil.matchesKeys(biomeName, HorizonsBiomes.REDWOOD_FOREST.getKey())) {
            withRedwoodForestFeatures(event.getGeneration(), event.getSpawns());
        }
    }

    public static void withRedwoodForestFeatures(BiomeGenerationSettingsBuilder builder, MobSpawnInfoBuilder spawns) {
        DefaultBiomeFeatures.withStrongholdAndMineshaft(builder);
        builder.withStructure(StructureFeatures.RUINED_PORTAL);
        DefaultBiomeFeatures.withCavesAndCanyons(builder);
        DefaultBiomeFeatures.withLavaAndWaterLakes(builder);
        DefaultBiomeFeatures.withMonsterRoom(builder);

        DefaultBiomeFeatures.withCommonOverworldBlocks(builder);
        DefaultBiomeFeatures.withOverworldOres(builder);
        DefaultBiomeFeatures.withDisks(builder);

        builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HorizonsFeatures.Configured.TREES_REDWOOD);
        builder.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HorizonsFeatures.Configured.PATCH_BLACKBERRY_DECORATED);
        DefaultBiomeFeatures.withAllForestFlowerGeneration(builder);
        DefaultBiomeFeatures.withDefaultFlowers(builder);
        DefaultBiomeFeatures.withForestGrass(builder);

        DefaultBiomeFeatures.withNormalMushroomGeneration(builder);
        DefaultBiomeFeatures.withSugarCaneAndPumpkins(builder);
        DefaultBiomeFeatures.withLavaAndWaterSprings(builder);
        DefaultBiomeFeatures.withFrozenTopLayer(builder);

        spawns
                .withSpawner(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.WOLF, 5, 4, 4))
                .isValidSpawnBiomeForPlayer();
    }
}
