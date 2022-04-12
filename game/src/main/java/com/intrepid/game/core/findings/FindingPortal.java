package com.intrepid.game.core.findings;

import com.intrepid.game.core.Complex;

public interface FindingPortal extends Finding {
    // Portal...

    static FindingPortal generateFor(Complex complex) {
        return CommonFindingPortal.generateFor(complex);
    }

    static String extractStep(FindingPortal finding) {
        return "* Biome: '" + finding.getBiome().getConfiguration().getDescription() + "'\n" +
                "* Local of Interest: '" + finding.getLocalOfInterest().getDescription() + "'\n"
                ;
    }
}
