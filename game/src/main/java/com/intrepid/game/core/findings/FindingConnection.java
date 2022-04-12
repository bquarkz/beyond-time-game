package com.intrepid.game.core.findings;

import com.intrepid.game.core.Complex;

public interface FindingConnection extends Finding {
    // Connection...

    static FindingConnection generateFor(Complex complex) {
        return CommonFindingConnection.generateFor(complex);
    }

    static String extractFirstStep(FindingConnection finding) {
        return "* Biome: '" + finding.getBiome().getConfiguration().getDescription() + "'\n" +
                "* Local of Interest: '" + finding.getLocalOfInterest().getDescription() + "'\n"
                ;
    }

    // ... more steps here
}
