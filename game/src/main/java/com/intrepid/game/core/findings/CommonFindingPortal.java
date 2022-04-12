package com.intrepid.game.core.findings;

import com.intrepid.game.core.Complex;
import com.intrepid.game.core.biomes.Biomes;
import com.intrepid.game.core.localofinterest.LocalOfInterest;
import com.intrepid.game.core.localofinterest.impl.Portal;

public class CommonFindingPortal implements FindingPortal {
    private final Complex complex;
    private LocalOfInterest localOfInterest;

    private CommonFindingPortal(Complex complex) {
        this.complex = complex;
    }

    @Override
    public FindingType getFindingType() {
        return FindingType.PORTAL;
    }

    @Override
    public Biomes getBiome() {
        return complex.getBiome();
    }

    @Override
    public LocalOfInterest getLocalOfInterest() {
        return localOfInterest;
    }

    private CommonFindingPortal localOfInterest(LocalOfInterest localOfInterest) {
        this.localOfInterest = localOfInterest;
        return this;
    }

    public static CommonFindingPortal generateFor(Complex complex) {
        return new CommonFindingPortal(complex)
                .localOfInterest(new Portal());
    }
}
