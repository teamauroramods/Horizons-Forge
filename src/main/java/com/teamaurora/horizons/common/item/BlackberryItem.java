package com.teamaurora.horizons.common.item;

import com.teamaurora.horizons.common.block.BlackberryBushBlock;
import com.teamaurora.horizons.core.registry.HorizonsBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraftforge.fml.ModList;

import javax.annotation.Nullable;

public class BlackberryItem extends BlockNamedItem {
    private final String compatModName;

    public BlackberryItem(String compatModName, Properties properties) {
        super(HorizonsBlocks.BLACKBERRY_BUSH.get(), properties);
        this.compatModName = compatModName;
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        return ModList.get().isLoaded(compatModName) ? ActionResultType.PASS : super.onItemUse(context);
    }
}
