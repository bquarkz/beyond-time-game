package com.intrepid.game.core.findings;

import com.intrepid.game.core.Complex;
import com.intrepid.game.core.biomes.Biomes;
import com.intrepid.game.core.localofinterest.LocalOfInterest;
import com.intrepid.nicge.utils.readonlycollections.ReadOnlyList;

import java.util.ArrayList;
import java.util.List;

public interface Finding {
    FindingType getFindingType();
    Biomes getBiome();
    LocalOfInterest getLocalOfInterest();

    static Finding randomGenerateFor(Complex complex) {
        final FindingType findingType = FindingType.random();
        switch (findingType) {
            default:
            case EXTRACTION:
                return FindingExtraction.generateFor(complex);
            case ENCOUNTER:
                return FindingEncounter.generateFor(complex);
            case PORTAL:
                return FindingPortal.generateFor(complex);
            case CONNECTION:
                return FindingConnection.generateFor(complex);
        }
    }

    static ReadOnlyList<String> processSteps(Finding finding) {
        final List<String> steps = new ArrayList<>();
        switch(finding.getFindingType()) {
            case EXTRACTION: {
                steps.add(FindingExtraction.extractFirstStep((FindingExtraction)finding));
                steps.add(FindingExtraction.extractSecondStep((FindingExtraction)finding));
            } break;

            case CONNECTION: {
                steps.add(FindingConnection.extractFirstStep((FindingConnection) finding));
            } break;

            case ENCOUNTER: {
                steps.add(FindingEncounter.extractStep((FindingEncounter)finding));
            } break;

            case PORTAL: {
                steps.add(FindingPortal.extractStep((FindingPortal) finding));
            } break;
        }
        return ReadOnlyList.wrap(steps);
    }

}
