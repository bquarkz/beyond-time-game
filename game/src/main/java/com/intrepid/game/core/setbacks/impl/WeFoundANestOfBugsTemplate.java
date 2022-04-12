package com.intrepid.game.core.setbacks.impl;

import com.intrepid.game.core.findings.Reward;
import com.intrepid.game.core.localofinterest.LocalOfInterestCollection;
import com.intrepid.game.core.setbacks.SetBack;
import com.intrepid.game.core.setbacks.SetBackBuilder;
import com.intrepid.game.core.setbacks.SetBackTemplate;
import com.intrepid.game.core.tasks.Task;
import com.intrepid.nicge.utils.readonlycollections.ReadOnlyList;
import com.intrepid.nicge.utils.readonlycollections.ReadOnlySet;

import static com.intrepid.game.core.localofinterest.LocalOfInterestCollection.BROKEN_PORTAL;
import static com.intrepid.game.core.localofinterest.LocalOfInterestCollection.DISABLED_LINES;

public class WeFoundANestOfBugsTemplate implements SetBackTemplate {
    @Override
    public ReadOnlySet<LocalOfInterestCollection> getLocalOfInterest() {
        return ReadOnlySet.of(DISABLED_LINES
                , BROKEN_PORTAL
                );
    }

    @Override
    public SetBack build(ReadOnlyList<Reward> rewards) {
        final int bugAttackPower = 1 + rewards.size();
        return SetBackBuilder
                .builder()
                .description("A Nest of cyber bugs have been found")
                .counterMeasure("Squad should keep defending themselves and survive against bug attacks everytime an extraction occurs! Bug Attack Power: " + bugAttackPower)
                .build();
    }
}
