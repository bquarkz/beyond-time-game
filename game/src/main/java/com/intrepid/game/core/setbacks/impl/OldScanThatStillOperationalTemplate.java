package com.intrepid.game.core.setbacks.impl;

import com.intrepid.game.core.Skills;
import com.intrepid.game.core.TestTypes;
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

public class OldScanThatStillOperationalTemplate implements SetBackTemplate {
    @Override
    public ReadOnlySet<LocalOfInterestCollection> getLocalOfInterest() {
        return ReadOnlySet.of(DISABLED_LINES
                , BROKEN_PORTAL
                );
    }

    @Override
    public SetBack build(ReadOnlyList<Reward> rewards) {
        final Integer quantityOfReward = rewards
                .stream()
                .map(Reward::getQuantity)
                .reduce(0, Integer::sum);
        final int skillValue = 3 + quantityOfReward / 25;
        final Task mitigation = new Task(TestTypes.PROBABILITY)
                .addTask(Skills.INFILTRATION.asRequirement(skillValue))
                .setDescription("It is needed to have " + skillValue + " success in a row for a full system shutdown");
        return SetBackBuilder
                .builder()
                .description("An old scan that still operational")
                .mitigation(mitigation)
                .counterMeasure("An alarm will trigger and squad will lose one point in Concealment Level")
                .build();
    }
}
