package com.teamaurora.horizons.core.registry;

import com.minecraftabnormals.abnormals_core.common.blocks.wood.AbnormalsLeavesBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.wood.AbnormalsLogBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.wood.AbnormalsSaplingBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.wood.StrippedLogBlock;
import com.minecraftabnormals.abnormals_core.core.util.registry.BlockSubRegistryHelper;
import com.teamaurora.horizons.common.block.BrittlePhaceliaBlock;
import com.teamaurora.horizons.common.block.trees.RedwoodTree;
import com.teamaurora.horizons.core.Horizons;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.trees.OakTree;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Horizons.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HorizonsBlocks {
    public static final BlockSubRegistryHelper HELPER = Horizons.REGISTRY_HELPER.getBlockSubHelper();

    public static final RegistryObject<Block> ATACAMA_SAND = HELPER.createBlock("atacama_sand", ()->new SandBlock(0xD2AF7F, Properties.SAND), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> COBBLED_TERRACOTTA = HELPER.createBlock("cobbled_terracotta", ()->new Block(Properties.TERRACOTTA), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> MUDCRACK = HELPER.createBlock("mudcrack", ()->new Block(Properties.MUDCRACK), ItemGroup.BUILDING_BLOCKS);

    public static final RegistryObject<Block> BRITTLE_PHACELIA = HELPER.createBlock("brittle_phacelia", ()->new BrittlePhaceliaBlock(()-> Effects.HASTE, 1, AbstractBlock.Properties.from(Blocks.ORANGE_TULIP)), ItemGroup.DECORATIONS); // TODO: Replace with copao cactus effect when implemented

    // redwood (will improve later, mainly just worldgen stuff rn
    public static final RegistryObject<Block> STRIPPED_REDWOOD_LOG = HELPER.createBlock("stripped_redwood_log", ()->new StrippedLogBlock(Block.Properties.from(Blocks.STRIPPED_OAK_LOG)), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> REDWOOD_LOG= HELPER.createBlock("redwood_log", ()->new AbnormalsLogBlock(STRIPPED_REDWOOD_LOG, Block.Properties.from(Blocks.OAK_LOG)), ItemGroup.BUILDING_BLOCKS);
    public static final RegistryObject<Block> REDWOOD_LEAVES = HELPER.createBlock("redwood_leaves", ()->new AbnormalsLeavesBlock(AbstractBlock.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT).notSolid().setAllowsSpawn((BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity)->(entity == EntityType.OCELOT || entity == EntityType.PARROT)).setSuffocates((BlockState state, IBlockReader reader, BlockPos pos)->false).setBlocksVision((BlockState state, IBlockReader reader, BlockPos pos)->false)), ItemGroup.DECORATIONS);
    public static final RegistryObject<Block> REDWOOD_SAPLING = HELPER.createBlock("redwood_sapling", ()->new AbnormalsSaplingBlock(new RedwoodTree(), Block.Properties.from(Blocks.OAK_SAPLING)), ItemGroup.DECORATIONS);


    static class Properties {
        public static final AbstractBlock.Properties SAND = AbstractBlock.Properties.from(Blocks.SAND);
        public static final AbstractBlock.Properties TERRACOTTA = AbstractBlock.Properties.from(Blocks.TERRACOTTA);

        public static final AbstractBlock.Properties MUDCRACK = AbstractBlock.Properties.from(Blocks.DIRT);
    }
}
