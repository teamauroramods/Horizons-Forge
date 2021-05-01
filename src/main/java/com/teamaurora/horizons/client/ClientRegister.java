package com.teamaurora.horizons.client;

import com.teamaurora.horizons.core.Horizons;
import com.teamaurora.horizons.core.registry.HorizonsBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Horizons.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRegister {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(ClientRegister::setupRenderLayer);
    }

    public static void setupRenderLayer() {
        RenderTypeLookup.setRenderLayer(HorizonsBlocks.BRITTLE_PHACELIA.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(HorizonsBlocks.REDWOOD_SAPLING.get(), RenderType.getCutout());
    }
}
