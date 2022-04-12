package com.intrepid.game.core.biomes;

import com.intrepid.game.core.resources.CommonResourcesChances;

public class AbandonedZone extends BiomeConfiguration {

    @Override
    public String getDescription() {
        return "Abandoned Zone";
    }

    @Override
    public CommonResourcesChances getResourceChances() {
        return CommonResourcesChances.with(1.0f, .75f, .5f, 1.25f, .5f, .25f);
    }

    @Override
    public int getChanceForNoSetBacks() {
        return 35;
    }
}
