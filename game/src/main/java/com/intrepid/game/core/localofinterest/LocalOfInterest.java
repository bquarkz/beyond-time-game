package com.intrepid.game.core.localofinterest;

import com.intrepid.game.core.Complex;
import com.intrepid.game.core.resources.CommonResourcesChances;
import com.intrepid.game.core.biomes.Biomes;
import com.intrepid.game.core.findings.Reward;
import com.intrepid.game.core.setbacks.SetBack;
import com.intrepid.game.core.TreasureRichness;
import com.intrepid.game.core.tasks.TaskRequirements;
import com.intrepid.nicge.utils.readonlycollections.ReadOnlyList;
import com.intrepid.nicge.utils.readonlycollections.ReadOnlySet;

public interface LocalOfInterest {
    String getDescription();
    ReadOnlySet<Biomes> getBiomes();
    CommonResourcesChances getResourceChances();
    TreasureRichness getTreasureRichness(int chance);
    int getInvestigationEffort(int effortOnExtraction, int numberOfRewards);
    TaskRequirements getExtraction(ReadOnlyList<Reward> rewards);
    ReadOnlyList<SetBack> getSetBacks(Complex complex, ReadOnlyList<Reward> rewards);
}
