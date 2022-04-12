package com.intrepid.game.core.localofinterest.impl;

import com.intrepid.game.core.biomes.Biomes;
import com.intrepid.game.core.localofinterest.BaseLocalOfInterest;
import com.intrepid.game.core.resources.CommonResourcesChances;
import com.intrepid.nicge.utils.readonlycollections.ReadOnlySet;

import static com.intrepid.game.core.biomes.Biomes.ABANDONED_ZONE;

public class BrokenPortal extends BaseLocalOfInterest {
    @Override
    public String getDescription() {
        return "Broken Portal";
    }

    @Override
    public ReadOnlySet<Biomes> getBiomes() {
        return ReadOnlySet.of(ABANDONED_ZONE);
    }

    @Override
    public CommonResourcesChances getResourceChances() {
        return CommonResourcesChances.with(.75, 0, .75, 0,.25,.25);
    }

    @Override
    protected int getMaxNumberOfSetBacks() {
        return 2;
    }

    @Override
    protected int getChanceForNoSetBacks() {
        return 25;
    }
}
