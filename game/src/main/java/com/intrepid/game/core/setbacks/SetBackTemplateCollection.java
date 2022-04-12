package com.intrepid.game.core.setbacks;

import com.intrepid.game.core.findings.Reward;
import com.intrepid.game.core.localofinterest.LocalOfInterestCollection;
import com.intrepid.game.core.setbacks.impl.EMPShockWaveMineTemplate;
import com.intrepid.game.core.setbacks.impl.OldScanThatStillOperationalTemplate;
import com.intrepid.game.core.setbacks.impl.WeFoundANestOfBugsTemplate;
import com.intrepid.nicge.utils.readonlycollections.ReadOnlyList;
import com.intrepid.nicge.utils.readonlycollections.ReadOnlySet;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SetBackTemplateCollection implements SetBackTemplate {
    OLD_SCAN_THAT_STILL_OPERATIONAL(new OldScanThatStillOperationalTemplate()),
    WE_FOUND_NEST_OF_BUGS(new WeFoundANestOfBugsTemplate()),
    EMP_SHOCK_WAVE_MINE(new EMPShockWaveMineTemplate()),
    ;

    private final SetBackTemplate setBackTemplate;

    SetBackTemplateCollection(SetBackTemplate setBackTemplate) {
        this.setBackTemplate = setBackTemplate;
    }

    @Override
    public ReadOnlySet<LocalOfInterestCollection> getLocalOfInterest() {
        return setBackTemplate.getLocalOfInterest();
    }

    @Override
    public SetBack build(ReadOnlyList<Reward> rewards) {
        return setBackTemplate.build(rewards);
    }

    public static ReadOnlySet<SetBackTemplate> findByLocalOfInterest(LocalOfInterestCollection localOfInterest) {
        return ReadOnlySet.of(Stream
                .of(values())
                .filter(template -> template.getLocalOfInterest().contains(localOfInterest))
                .collect(Collectors.toSet()));
    }
}
