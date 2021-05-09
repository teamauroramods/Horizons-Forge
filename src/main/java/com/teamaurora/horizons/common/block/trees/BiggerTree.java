package com.teamaurora.horizons.common.block.trees;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.trees.Tree;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public abstract class BiggerTree extends Tree {
    @Override
    public boolean attemptGrowTree(ServerWorld world, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, Random rand) {
        for(int i = 0; i < 4; i++) {
            if (canBigTreeSpawnAt(state, world, pos.offset(Direction.byHorizontalIndex(i)))) {
                return this.growBigTree(world, chunkGenerator, pos.offset(Direction.byHorizontalIndex(i)), state, rand);
            }
        }
        if (canBigTreeSpawnAt(state, world, pos)) {
            return this.growBigTree(world, chunkGenerator, pos, state, rand);
        }

        return super.attemptGrowTree(world, chunkGenerator, pos, state, rand);
    }

    /**
     * Get a {@link ConfiguredFeature} of the huge variant of this tree
     */
    @Nullable
    protected abstract ConfiguredFeature<BaseTreeFeatureConfig, ?> getHugeTreeFeature(Random rand);

    public boolean growBigTree(ServerWorld world, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, Random rand) {
        ConfiguredFeature<BaseTreeFeatureConfig, ?> configuredfeature = this.getHugeTreeFeature(rand);
        if (configuredfeature == null) {
            return false;
        } else {
            configuredfeature.config.forcePlacement();
            BlockState blockstate = Blocks.AIR.getDefaultState();
            for (int i = 0; i < 4; i++) {
                world.setBlockState(pos.offset(Direction.byHorizontalIndex(i)), blockstate, 4);
            }
            if (configuredfeature.generate(world, chunkGenerator, rand, pos)) {
                return true;
            } else {
                for (int i = 0; i < 4; i++) {
                    world.setBlockState(pos.offset(Direction.byHorizontalIndex(i)), state, 4);
                }
                return false;
            }
        }
    }

    public static boolean canBigTreeSpawnAt(BlockState blockUnder, IBlockReader worldIn, BlockPos pos) {
        Block block = blockUnder.getBlock();
        for (int i = 0; i < 4; i++) {
            if (block != worldIn.getBlockState(pos.offset(Direction.byHorizontalIndex(i))).getBlock()) {
                return false;
            }
        }
        return block == worldIn.getBlockState(pos).getBlock();
    }
}
