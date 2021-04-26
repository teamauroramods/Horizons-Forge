package com.teamaurora.horizons.core;

import com.minecraftabnormals.abnormals_core.core.util.registry.RegistryHelper;
import com.teamaurora.horizons.core.other.HorizonsData;
import com.teamaurora.horizons.core.registry.HorizonsBiomes;
import com.teamaurora.horizons.core.registry.HorizonsSurfaceBuilders;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.teamaurora.horizons.core.Horizons.MODID;

@Mod(MODID)
public class Horizons
{
    public static final String MODID = "horizons";
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MODID);

    public Horizons() {
        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        REGISTRY_HELPER.register(eventBus);

        eventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            HorizonsSurfaceBuilders.Configured.registerConfiguredSurfaceBuilders();
            HorizonsBiomes.registerBiomesToDictionary();
            HorizonsBiomes.addBiomeTypes();
            HorizonsBiomes.addHillBiomes();
            HorizonsData.registerCompostables();
        });
    }
}