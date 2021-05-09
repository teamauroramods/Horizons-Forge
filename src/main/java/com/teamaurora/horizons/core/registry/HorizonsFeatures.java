package com.teamaurora.horizons.core.registry;

import com.google.common.collect.ImmutableList;
import com.teamaurora.horizons.common.world.gen.feature.BigRedwoodFeature;
import com.teamaurora.horizons.common.world.gen.feature.RedwoodFeature;
import com.teamaurora.horizons.common.world.gen.treedecorator.RedwoodBushTreeDecorator;
import com.teamaurora.horizons.core.Horizons;
import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = Horizons.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HorizonsFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Horizons.MODID);
    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATORS = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, Horizons.MODID);

    public static final RegistryObject<Feature<BaseTreeFeatureConfig>> REDWOOD_TREE = FEATURES.register("redwood_tree", ()->new RedwoodFeature(BaseTreeFeatureConfig.CODEC, false));
    public static final RegistryObject<Feature<BaseTreeFeatureConfig>> LARGE_REDWOOD_TREE = FEATURES.register("large_redwood_tree", ()->new BigRedwoodFeature(BaseTreeFeatureConfig.CODEC, false));
    public static final RegistryObject<Feature<BaseTreeFeatureConfig>> REDWOOD_TREE_SAPLING = FEATURES.register("redwood_tree_sapling", ()->new RedwoodFeature(BaseTreeFeatureConfig.CODEC, true));
    public static final RegistryObject<Feature<BaseTreeFeatureConfig>> LARGE_REDWOOD_TREE_SAPLING = FEATURES.register("large_redwood_tree_sapling", ()->new BigRedwoodFeature(BaseTreeFeatureConfig.CODEC, true));

    public static final RegistryObject<TreeDecoratorType<?>> REDWOOD_BUSH = TREE_DECORATORS.register("redwood_bush", () -> new TreeDecoratorType<>(RedwoodBushTreeDecorator.CODEC));

    public static final class BlockStates {
        public static final BlockState REDWOOD_LOG = HorizonsBlocks.REDWOOD_LOG.get().getDefaultState();
        public static final BlockState REDWOOD_LEAVES = HorizonsBlocks.REDWOOD_LEAVES.get().getDefaultState();
    }

    public static final class Configs {
        public static final BaseTreeFeatureConfig REDWOOD_TREE_CONFIG = (new BaseTreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(BlockStates.REDWOOD_LOG),
                new SimpleBlockStateProvider(BlockStates.REDWOOD_LEAVES),
                new BlobFoliagePlacer(FeatureSpread.func_242252_a(0), FeatureSpread.func_242252_a(0), 0),
                new StraightTrunkPlacer(0, 0, 0),
                new TwoLayerFeature(0, 0, 0)
        )).setIgnoreVines().setDecorators(ImmutableList.of(RedwoodBushTreeDecorator.DECORATOR)).build();
    }

    public static final class Configured {
        public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> REDWOOD = REDWOOD_TREE.get().withConfiguration(Configs.REDWOOD_TREE_CONFIG);
        public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> LARGE_REDWOOD = LARGE_REDWOOD_TREE.get().withConfiguration(Configs.REDWOOD_TREE_CONFIG);
        public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> REDWOOD_SAPLING = REDWOOD_TREE_SAPLING.get().withConfiguration(Configs.REDWOOD_TREE_CONFIG);
        public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> LARGE_REDWOOD_SAPLING = LARGE_REDWOOD_TREE_SAPLING.get().withConfiguration(Configs.REDWOOD_TREE_CONFIG);

        private static <FC extends IFeatureConfig> void register(String name, ConfiguredFeature<FC, ?> configuredFeature) {
            Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(Horizons.MODID, name), configuredFeature);
        }

        public static void registerConfiguredFeatures() {
            register("redwood", REDWOOD);
            register("large_redwood", LARGE_REDWOOD);
            register("redwood_sapling", REDWOOD_SAPLING);
            register("large_redwood_sapling", LARGE_REDWOOD_SAPLING);
        }
    }
}
