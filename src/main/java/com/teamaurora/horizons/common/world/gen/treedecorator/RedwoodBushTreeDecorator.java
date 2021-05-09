package com.teamaurora.horizons.common.world.gen.treedecorator;

import com.mojang.serialization.Codec;
import com.teamaurora.horizons.core.registry.HorizonsBlocks;
import com.teamaurora.horizons.core.registry.HorizonsFeatures;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class RedwoodBushTreeDecorator extends TreeDecorator {
    public static final Codec<RedwoodBushTreeDecorator> CODEC;
    public static final RedwoodBushTreeDecorator DECORATOR = new RedwoodBushTreeDecorator();

    @Override
    protected TreeDecoratorType<?> func_230380_a_() {
        return HorizonsFeatures.REDWOOD_BUSH.get();
    }

    static {
        CODEC = Codec.unit(() -> DECORATOR);
    }

    @Override
    public void func_225576_a_(ISeedReader world, Random rand, List<BlockPos> logs, List<BlockPos> leaves, Set<BlockPos> updatedBlocks, MutableBoundingBox bounds) {
        int minLeavesY = world.getHeight();
        for (BlockPos pos : leaves) {
            if (pos.getY() < minLeavesY) minLeavesY = pos.getY();
        }

        List<BlockPos> pogs = new ArrayList<>();
        for (BlockPos pos : logs) {
            if (pos.getY() < minLeavesY - 2) pogs.add(new BlockPos(pos));
        }

        int numBushes = rand.nextInt(2);
        if (pogs.size() > 38) {
            numBushes = rand.nextInt(3);
        }

        for (int i = 0; i < numBushes; i++) {
            bush(world, pogs.get(rand.nextInt(pogs.size())));
        }
    }

    private void bush(ISeedReader world, BlockPos pos) {
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                if (Math.abs(x) != 2 || Math.abs(z) != 2) {
                    if (world.getBlockState(pos.add(x, 0, z)).isAir()) {
                        world.setBlockState(pos.add(x, 0, z), HorizonsBlocks.REDWOOD_LEAVES.get().getDefaultState().with(LeavesBlock.PERSISTENT, false), 3);
                    }
                }
            }
        }
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                if (Math.abs(x) != 1 || Math.abs(z) != 1) {
                    if (world.getBlockState(pos.add(x, 1, z)).isAir()) {
                        world.setBlockState(pos.add(x, 1, z), HorizonsBlocks.REDWOOD_LEAVES.get().getDefaultState().with(LeavesBlock.PERSISTENT, false), 3);
                    }
                }
            }
        }
    }
}
