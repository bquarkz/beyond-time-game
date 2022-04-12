package com.intrepid.game.core.setbacks;

import com.intrepid.game.core.findings.Reward;
import com.intrepid.game.core.localofinterest.LocalOfInterestCollection;
import com.intrepid.nicge.utils.readonlycollections.ReadOnlyList;
import com.intrepid.nicge.utils.readonlycollections.ReadOnlySet;

public interface SetBackTemplate {
    ReadOnlySet<LocalOfInterestCollection> getLocalOfInterest();
    SetBack build(ReadOnlyList<Reward> rewards);
}
