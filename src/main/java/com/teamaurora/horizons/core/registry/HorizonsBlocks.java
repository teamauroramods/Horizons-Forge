package com.teamaurora.horizons.core.registry;

import com.minecraftabnormals.abnormals_core.core.util.registry.BlockSubRegistryHelper;
import com.teamaurora.horizons.common.block.BrittlePhaceliaBlock;
import com.teamaurora.horizons.core.Horizons;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SandBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Horizons.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HorizonsBlocks {
    public static final BlockSubRegistryHelper HELPER = Horizons.REGISTRY_HELPER.getBlockSubHelper();

    public static final RegistryObject<Block> ATACAMA_SAND = HELPER.createBlock("atacama_sand", ()->new SandBlock(0xD2AF7F, Properties.SAND), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> COBBLED_TERRACOTTA = HELPER.createBlock("cobbled_terracotta", ()->new Block(Properties.TERRACOTTA), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> MUDCRACK = HELPER.createBlock("mudcrack", ()->new Block(Properties.MUDCRACK), ItemGroup.BUILDING_BLOCKS);

    public static final RegistryObject<Block> BRITTLE_PHACELIA = HELPER.createBlock("brittle_phacelia", ()->new BrittlePhaceliaBlock(()-> Effects.HASTE, 1, AbstractBlock.Properties.from(Blocks.ORANGE_TULIP)), ItemGroup.DECORATIONS); // TODO: Replace with copao cactus effect when implemented

    static class Properties {
        public static final AbstractBlock.Properties SAND = AbstractBlock.Properties.from(Blocks.SAND);
        public static final AbstractBlock.Properties TERRACOTTA = AbstractBlock.Properties.from(Blocks.TERRACOTTA);

        public static final AbstractBlock.Properties MUDCRACK = AbstractBlock.Properties.from(Blocks.DIRT);
    }
}
