package com.teamaurora.horizons.common.block.trees;

import com.teamaurora.horizons.core.registry.HorizonsFeatures;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class RedwoodTree extends BiggerTree {
    @Nullable
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(@Nullable Random randomIn, boolean largeHive) {
        return HorizonsFeatures.Configured.REDWOOD_SAPLING;
    }

    @Nullable
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getHugeTreeFeature(@Nullable Random randomIn) {
        return HorizonsFeatures.Configured.LARGE_REDWOOD_SAPLING;
    }
}
