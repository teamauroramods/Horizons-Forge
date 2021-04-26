package com.teamaurora.horizons.core.registry;

import com.minecraftabnormals.abnormals_core.core.util.BiomeUtil;
import com.minecraftabnormals.abnormals_core.core.util.registry.BiomeSubRegistryHelper;
import com.mojang.datafixers.util.Pair;
import com.teamaurora.horizons.core.Horizons;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Horizons.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HorizonsBiomes {
    private static final BiomeSubRegistryHelper HELPER = Horizons.REGISTRY_HELPER.getBiomeSubHelper();

    public static final BiomeSubRegistryHelper.KeyedBiome ATACAMA_DESERT = HELPER.createBiome("atacama_desert", ()->makeAtacamaBiome(0.1F, 0.2F));
    public static final BiomeSubRegistryHelper.KeyedBiome ATACAMA_PLATEAU = HELPER.createBiome("atacama_plateau", ()->makeAtacamaBiome(1.5F, 0.025F));

    public static void addHillBiomes() {
        BiomeUtil.addHillBiome(ATACAMA_DESERT.getKey(), Pair.of(ATACAMA_PLATEAU.getKey(), 1));
    }

    public static void registerBiomesToDictionary() {
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(ATACAMA_DESERT.getKey(), 2));
    }

    public static void addBiomeTypes() {
        BiomeDictionary.addTypes(ATACAMA_DESERT.getKey(), BiomeDictionary.Type.HOT, BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.DRY, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.OVERWORLD);
        BiomeDictionary.addTypes(ATACAMA_PLATEAU.getKey(), BiomeDictionary.Type.HOT, BiomeDictionary.Type.SPARSE, BiomeDictionary.Type.DRY, BiomeDictionary.Type.PLATEAU, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.OVERWORLD);
    }

    private static Biome makeAtacamaBiome(float depth, float scale) {
        return (new Biome.Builder())
                .precipitation(Biome.RainType.NONE)
                .category(Biome.Category.DESERT)
                .depth(depth)
                .scale(scale)
                .temperature(2.0F)
                .downfall(0.0F)
                .setEffects((new BiomeAmbience.Builder())
                        .setWaterColor(4159204)
                        .setWaterFogColor(329011)
                        .setFogColor(12638463)
                        .withSkyColor(getSkyColorWithTemperatureModifier(2.0F))
                        .setMoodSound(MoodSoundAmbience.DEFAULT_CAVE)
                        .build())
                .withMobSpawnSettings(new MobSpawnInfo.Builder().copy())
                .withGenerationSettings((new BiomeGenerationSettings.Builder())
                        .withSurfaceBuilder(HorizonsSurfaceBuilders.Configured.ATACAMA)
                        .build()).build();
    }

    private static int getSkyColorWithTemperatureModifier(float temperature) {
        float lvt_1_1_ = temperature / 3.0F;
        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
        return MathHelper.hsvToRGB(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }
}
