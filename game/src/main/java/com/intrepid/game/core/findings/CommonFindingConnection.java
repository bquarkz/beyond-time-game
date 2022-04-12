package com.intrepid.game.core.findings;

import com.intrepid.game.core.Complex;
import com.intrepid.game.core.biomes.Biomes;
import com.intrepid.game.core.localofinterest.LocalOfInterest;

import static com.intrepid.game.core.findings.FindingZUtils.randomLocalOfInterest;

public class CommonFindingConnection implements FindingConnection {
    private final Complex complex;
    private LocalOfInterest localOfInterest;

    private CommonFindingConnection(Complex complex) {
        this.complex = complex;
    }

    @Override
    public FindingType getFindingType() {
        return FindingType.CONNECTION;
    }

    @Override
    public Biomes getBiome() {
        return complex.getBiome();
    }

    @Override
    public LocalOfInterest getLocalOfInterest() {
        return localOfInterest;
    }

    private CommonFindingConnection localOfInterest(LocalOfInterest localOfInterest) {
        this.localOfInterest = localOfInterest;
        return this;
    }

    public static CommonFindingConnection generateFor(Complex complex) {
        final LocalOfInterest localOfInterest = randomLocalOfInterest(complex.getBiome());

        return new CommonFindingConnection(complex)
                .localOfInterest(localOfInterest);
    }
}
