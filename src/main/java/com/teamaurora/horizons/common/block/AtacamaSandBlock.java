package com.teamaurora.horizons.common.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.SandBlock;
import net.minecraft.block.material.Material;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.PlantType;

public class AtacamaSandBlock extends SandBlock {
    public AtacamaSandBlock(int dustColorIn, Properties properties) {
        super(dustColorIn, properties);
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader blockReader, BlockPos pos, Direction direction, IPlantable iPlantable) {
        final BlockPos plantPos = new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ());
        final PlantType plantType = iPlantable.getPlantType(blockReader, plantPos);
        if (plantType == PlantType.DESERT) {
            return true;
        } else if (plantType == PlantType.WATER) {
            return blockReader.getBlockState(pos).getMaterial() == Material.WATER && blockReader.getBlockState(pos) == getDefaultState();
        } else if (plantType == PlantType.BEACH) {
            return ((blockReader.getBlockState(pos.east()).getMaterial() == Material.WATER || blockReader.getBlockState(pos.east()).hasProperty(BlockStateProperties.WATERLOGGED))
                    || (blockReader.getBlockState(pos.west()).getMaterial() == Material.WATER || blockReader.getBlockState(pos.west()).hasProperty(BlockStateProperties.WATERLOGGED))
                    || (blockReader.getBlockState(pos.north()).getMaterial() == Material.WATER || blockReader.getBlockState(pos.north()).hasProperty(BlockStateProperties.WATERLOGGED))
                    || (blockReader.getBlockState(pos.south()).getMaterial() == Material.WATER || blockReader.getBlockState(pos.south()).hasProperty(BlockStateProperties.WATERLOGGED)));
        } else {
            return false;
        }
    }
}
