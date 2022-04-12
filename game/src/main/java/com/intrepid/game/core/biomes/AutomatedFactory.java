package com.intrepid.game.core.biomes;

import com.intrepid.game.core.resources.CommonResourcesChances;

public class AutomatedFactory extends BiomeConfiguration {

    @Override
    public String getDescription() {
        return "Automated Factory";
    }

    @Override
    public CommonResourcesChances getResourceChances() {
        return CommonResourcesChances.with(1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f);
    }

    @Override
    public int getChanceForNoSetBacks() {
        return 0;
    }
}
