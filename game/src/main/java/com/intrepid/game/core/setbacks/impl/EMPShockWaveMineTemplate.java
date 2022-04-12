package com.intrepid.game.core.setbacks.impl;

import com.intrepid.game.core.Skills;
import com.intrepid.game.core.TestTypes;
import com.intrepid.game.core.findings.Reward;
import com.intrepid.game.core.localofinterest.LocalOfInterestCollection;
import com.intrepid.game.core.setbacks.SetBack;
import com.intrepid.game.core.setbacks.SetBackBuilder;
import com.intrepid.game.core.setbacks.SetBackTemplate;
import com.intrepid.game.core.tasks.AnyTaskRequirements;
import com.intrepid.game.core.tasks.Task;
import com.intrepid.nicge.utils.readonlycollections.ReadOnlyList;
import com.intrepid.nicge.utils.readonlycollections.ReadOnlySet;

import static com.intrepid.game.core.resources.CommonResources.ELECTRONIC_COMPONENTS;

public class EMPShockWaveMineTemplate implements SetBackTemplate {
    @Override
    public ReadOnlySet<LocalOfInterestCollection> getLocalOfInterest() {
        return ReadOnlySet.of(LocalOfInterestCollection.DISABLED_LINES);
    }

    @Override
    public SetBack build(ReadOnlyList<Reward> rewards) {
        if (!Reward.hasAllCodes(rewards, ELECTRONIC_COMPONENTS.getCode())) {
            return SetBack.NOT_SETBACK;
        }

        final String description = "EMP mines have been exposed";

        final int difficult = rewards.size() * 25;
        final Task mitigation = new Task(TestTypes.PROGRESS)
                .addTask(new AnyTaskRequirements()
                        .add(Skills.COMBAT.asRequirement(difficult))
                        .add(Skills.ENGINEERING.asRequirement(difficult)))
                .asInterchangeablyTest();

        final String counterMeasure = "If not mitigated, all [" +
                ELECTRONIC_COMPONENTS.getDescription() +
                "] will be burned out from current reward";

        return SetBackBuilder
                .builder()
                .description(description)
                .mitigation(mitigation)
                .counterMeasure(counterMeasure)
                .build();
    }
}
