package com.intrepid.game.core.findings;

import com.intrepid.game.core.Complex;
import com.intrepid.game.core.biomes.Biomes;
import com.intrepid.game.core.encounters.Encounter;
import com.intrepid.game.core.localofinterest.LocalOfInterest;

import static com.intrepid.game.core.findings.FindingZUtils.randomLocalOfInterest;

public class CommonFindingEncounter implements FindingEncounter {
    private final Complex complex;
    private LocalOfInterest localOfInterest;
    private Encounter encounter;

    private CommonFindingEncounter(Complex complex) {
        this.complex = complex;
    }

    @Override
    public FindingType getFindingType() {
        return FindingType.ENCOUNTER;
    }

    @Override
    public Biomes getBiome() {
        return complex.getBiome();
    }

    @Override
    public LocalOfInterest getLocalOfInterest() {
        return localOfInterest;
    }

    @Override
    public Encounter getEncounter() {
        return encounter;
    }

    private CommonFindingEncounter localOfInterest(LocalOfInterest localOfInterest) {
        this.localOfInterest = localOfInterest;
        return this;
    }

    private CommonFindingEncounter encounter(Encounter encounter) {
        this.encounter = encounter;
        return this;
    }

    public static CommonFindingEncounter generateFor(Complex complex) {
        final LocalOfInterest localOfInterest = randomLocalOfInterest(complex.getBiome());
        final Encounter encounter = FindingZUtils.randomEncounter(complex.getBiome());

        return new CommonFindingEncounter(complex)
                .localOfInterest(localOfInterest)
                .encounter(encounter);
    }

}
