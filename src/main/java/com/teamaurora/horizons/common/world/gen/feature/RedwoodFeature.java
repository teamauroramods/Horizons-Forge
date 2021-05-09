package com.teamaurora.horizons.common.world.gen.feature;

import com.google.common.collect.Sets;
import com.minecraftabnormals.abnormals_core.core.util.TreeUtil;
import com.mojang.serialization.Codec;
import com.teamaurora.horizons.core.registry.HorizonsBlocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.*;

public class RedwoodFeature extends Feature<BaseTreeFeatureConfig> {
    private final boolean fromSapling;

    public RedwoodFeature(Codec<BaseTreeFeatureConfig> codec, boolean fromSapling) {
        super(codec);
        this.fromSapling = fromSapling;
    }

    @Override
    public boolean generate(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos position, BaseTreeFeatureConfig config) {
        int bottomHeight = rand.nextInt(3) + 4;
        int topHeight = bottomHeight + rand.nextInt(5) + 16;
        if (position.getY() <= 0 || position.getY() + topHeight > worldIn.getHeight() - 4) {
            return false;
        }
        if (!TreeUtil.isValidGround(worldIn, position.down(), (SaplingBlock) HorizonsBlocks.REDWOOD_SAPLING.get())) {
            return false;
        }

        List<BlockPos> logs = new ArrayList<>();
        List<BlockPos> leaves = new ArrayList<>();

        for (int y = 0; y < topHeight; y++) {
            if (y < bottomHeight) {
                for (int i = 0; i < 4; i++) {
                    logs.add(position.up(y).offset(Direction.byHorizontalIndex(i)));
                }
            }
            logs.add(position.up(y));
        }

        if (!fromSapling) {
            for (int y = 1; y <= 5; y++) {
                for (int i = 0; i < 4; i++) {
                    BlockPos pos = position.down(y).offset(Direction.byHorizontalIndex(i));
                    if (TreeUtil.isAir(worldIn, pos)) {
                        logs.add(pos);
                    }
                }
                if (TreeUtil.isAir(worldIn, position.down(y))) {
                    logs.add(position.down(y));
                }
            }
        }

        disc2(position.up(topHeight-1), leaves);
        disc2(position.up(topHeight-2), leaves);
        disc2(position.up(topHeight-3), leaves);
        sqr1(position.up(topHeight-4), leaves);
        disc2(position.up(topHeight-5), leaves);
        disc2(position.up(topHeight-6), leaves);
        disc2(position.up(topHeight-7), leaves);
        disc2(position.up(topHeight-8), leaves);
        sqr1(position.up(topHeight-9), leaves);
        disc2(position.up(topHeight-10), leaves);
        disc2(position.up(topHeight-11), leaves);
        disc2(position.up(topHeight-12), leaves);
        disc2(position.up(topHeight-13), leaves);
        sqr1(position.up(topHeight-14), leaves);

        sqr1(position.up(topHeight), leaves);
        disc1(position.up(topHeight+1), leaves);
        leaves.add(position.up(topHeight+2));
        leaves.add(position.up(topHeight+3));

        List<BlockPos> leavesClean = cleanLeavesArray(leaves, logs);

        boolean flag = true;
        for (BlockPos log : logs) {
            if (!TreeUtil.isAirOrLeaves(worldIn, log)) {
                flag = false;
            }
        }
        if (!flag) return false;

        TreeUtil.setDirtAt(worldIn, position.down());

        for (BlockPos log : logs) {
            TreeUtil.placeLogAt(worldIn, log, rand, config);
        }
        for (BlockPos leaf : leavesClean) {
            TreeUtil.placeLeafAt(worldIn, leaf, rand, config);
        }

        Set<BlockPos> decSet = Sets.newHashSet();
        MutableBoundingBox mutableBoundingBox = MutableBoundingBox.getNewBoundingBox();

        List<BlockPos> logsPos = new ArrayList<>();
        for (BlockPos log : logs) {
            logsPos.add(log);
        }

        if (!config.decorators.isEmpty()) {
            logsPos.sort(Comparator.comparingInt(Vector3i::getY));
            leavesClean.sort(Comparator.comparingInt(Vector3i::getY));
            config.decorators.forEach((decorator) -> decorator.func_225576_a_(worldIn, rand, logsPos, leavesClean, decSet, mutableBoundingBox));
        }

        return true;
    }

    private void disc1(BlockPos pos, List<BlockPos> leaves) {
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                if (Math.abs(x) != 1 || Math.abs(z) != 1) {
                    leaves.add(pos.add(x, 0, z));
                }
            }
        }
    }

    private void sqr1(BlockPos pos, List<BlockPos> leaves) {
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                leaves.add(pos.add(x, 0, z));
            }
        }
    }

    private void disc2(BlockPos pos, List<BlockPos> leaves) {
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                if (Math.abs(x) != 2 || Math.abs(z) != 2) {
                    leaves.add(pos.add(x, 0, z));
                }
            }
        }
    }

    private List<BlockPos> cleanLeavesArray(List<BlockPos> leaves, List<BlockPos> logs) {
        List<BlockPos> newLeaves = new ArrayList<>();
        for (BlockPos leaf : leaves) {
            if (!logs.contains(leaf)) {
                newLeaves.add(leaf);
            }
        }
        return newLeaves;
    }
}