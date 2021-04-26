package com.teamaurora.horizons.core.registry;

import com.teamaurora.horizons.common.world.gen.surfacebuilders.AtacamaSurfaceBuilder;
import com.teamaurora.horizons.core.Horizons;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Horizons.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HorizonsSurfaceBuilders {
    public static final SurfaceBuilder<SurfaceBuilderConfig> ATACAMA = new AtacamaSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_);

    @SubscribeEvent
    public static void registerSurfaceBuilders(RegistryEvent.Register<SurfaceBuilder<?>> event) {
        event.getRegistry().register(ATACAMA.setRegistryName(Horizons.MODID, "atacama"));
    }

    public static final class Configs {
        public static final SurfaceBuilderConfig ATACAMA = new SurfaceBuilderConfig(HorizonsBlocks.ATACAMA_SAND.get().getDefaultState(), HorizonsBlocks.ATACAMA_SAND.get().getDefaultState(), Blocks.GRAVEL.getDefaultState());
    }

    public static final class Configured {
        public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> ATACAMA = HorizonsSurfaceBuilders.ATACAMA.func_242929_a(Configs.ATACAMA);

        private static <SC extends ISurfaceBuilderConfig> void register(String key, ConfiguredSurfaceBuilder<SC> builder) {
            WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, new ResourceLocation(Horizons.MODID, key), builder);
        }

        public static void registerConfiguredSurfaceBuilders() {
            register("atacama", ATACAMA);
        }
    }
}
