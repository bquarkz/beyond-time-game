package com.intrepid.game.core.biomes;

import com.intrepid.game.core.resources.CommonResourcesChances;

public abstract class BiomeConfiguration {
    public abstract String getDescription();
    public abstract CommonResourcesChances getResourceChances();
    public abstract int getChanceForNoSetBacks();
}
