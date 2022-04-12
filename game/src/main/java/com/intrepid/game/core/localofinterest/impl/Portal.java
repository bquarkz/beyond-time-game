package com.intrepid.game.core.localofinterest.impl;

import com.intrepid.game.core.Complex;
import com.intrepid.game.core.TreasureRichness;
import com.intrepid.game.core.biomes.Biomes;
import com.intrepid.game.core.findings.Reward;
import com.intrepid.game.core.localofinterest.LocalOfInterest;
import com.intrepid.game.core.resources.CommonResourcesChances;
import com.intrepid.game.core.setbacks.SetBack;
import com.intrepid.game.core.tasks.Task;
import com.intrepid.game.core.tasks.TaskRequirements;
import com.intrepid.nicge.utils.readonlycollections.ReadOnlyList;
import com.intrepid.nicge.utils.readonlycollections.ReadOnlySet;

public class Portal implements LocalOfInterest {
    @Override
    public String getDescription() {
        return "SSA Portal";
    }

    @Override
    public ReadOnlySet<Biomes> getBiomes() {
        return ReadOnlySet.of(Biomes.values());
    }

    @Override
    public CommonResourcesChances getResourceChances() {
        return CommonResourcesChances.none();
    }

    @Override
    public TreasureRichness getTreasureRichness(int chance) {
        return TreasureRichness.NORMAL;
    }

    @Override
    public int getInvestigationEffort(int effortOnExtraction, int numberOfRewards) {
        return 0;
    }

    @Override
    public TaskRequirements getExtraction(ReadOnlyList<Reward> rewards) {
        return Task.EMPTY_TASK;
    }

    @Override
    public ReadOnlyList<SetBack> getSetBacks(Complex complex, ReadOnlyList<Reward> rewards) {
        return ReadOnlyList.empty();
    }
}
