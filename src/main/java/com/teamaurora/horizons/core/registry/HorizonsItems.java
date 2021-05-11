package com.teamaurora.horizons.core.registry;

import com.minecraftabnormals.abnormals_core.core.util.registry.ItemSubRegistryHelper;
import com.teamaurora.horizons.common.item.BlackberryItem;
import com.teamaurora.horizons.core.Horizons;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Horizons.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HorizonsItems {
    public static final ItemSubRegistryHelper HELPER = Horizons.REGISTRY_HELPER.getItemSubHelper();

    public static final RegistryObject<Item> BLACKBERRY = HELPER.createItem("blackberry", ()->new BlackberryItem("berry_good", new Item.Properties().food(Foods.BLACKBERRY).group(ItemGroup.FOOD)));
    public static final RegistryObject<Item> BLACKBERRY_PIPS = HELPER.createItem("blackberry_pips", ()->new BlockNamedItem(HorizonsBlocks.BLACKBERRY_PIPS.get(), new Item.Properties().group(ModList.get().isLoaded("berry_good") ? ItemGroup.MISC : null)));
    
    public static class Foods {
        public static final Food BLACKBERRY = (new Food.Builder()).hunger(2).saturation(0.1F).build(); // temporarily just Sweet Berries
    }
}
