package com.intrepid.game.core.findings;

import com.intrepid.game.core.Complex;
import com.intrepid.game.core.encounters.Encounter;

public interface FindingEncounter extends Finding {
    Encounter getEncounter();

    static FindingEncounter generateFor(Complex complex) {
        return CommonFindingEncounter.generateFor(complex);
    }

    static String extractStep(FindingEncounter finding) {
        return "* Biome: '" + finding.getBiome().getConfiguration().getDescription() + "'\n" +
                "* Local of Interest: '" + finding.getLocalOfInterest().getDescription() + "'\n"
                ;
    }
}
