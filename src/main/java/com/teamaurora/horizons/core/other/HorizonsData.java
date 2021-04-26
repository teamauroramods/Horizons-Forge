package com.teamaurora.horizons.core.other;

import com.minecraftabnormals.abnormals_core.core.util.DataUtil;
import com.teamaurora.horizons.core.registry.HorizonsBlocks;

public class HorizonsData {
    public static void registerCompostables() {
        DataUtil.registerCompostable(HorizonsBlocks.BRITTLE_PHACELIA.get(), 0.65F);
    }
}
