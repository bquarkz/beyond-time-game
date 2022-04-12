package com.intrepid.game.core.localofinterest.impl;

import com.intrepid.game.core.TreasureRichness;
import com.intrepid.game.core.biomes.Biomes;
import com.intrepid.game.core.localofinterest.BaseLocalOfInterest;
import com.intrepid.game.core.resources.CommonResourcesChances;
import com.intrepid.nicge.utils.readonlycollections.ReadOnlySet;

public class DisabledLines extends BaseLocalOfInterest {
    @Override
    protected int getMaxNumberOfSetBacks() {
        return 1;
    }

    @Override
    protected int getChanceForNoSetBacks() {
        return 50;
    }

    @Override
    public String getDescription() {
        return "Disabled Lines";
    }

    @Override
    public ReadOnlySet<Biomes> getBiomes() {
        return ReadOnlySet.of(Biomes.AUTOMATED_FACTORY, Biomes.ABANDONED_ZONE);
    }

    @Override
    public CommonResourcesChances getResourceChances() {
        return CommonResourcesChances.with(1.0f, .75f, .75f, 1.0f, .75f, .0f);
    }

    @Override
    public TreasureRichness getTreasureRichness(int chance) {
        if (chance < 45) {
            return TreasureRichness.EXTREMELY_POOR;
        } else if (chance < 85) {
            return TreasureRichness.POOR;
        } else {
            return TreasureRichness.NORMAL;
        }
    }
}
