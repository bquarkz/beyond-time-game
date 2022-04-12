package com.intrepid.game.core.findings;

import com.intrepid.game.core.Complex;
import com.intrepid.game.core.setbacks.SetBack;
import com.intrepid.game.core.tasks.Task;
import com.intrepid.nicge.utils.readonlycollections.ReadOnlyList;

public interface FindingExtraction extends Finding{
    Task getInvestigation();
    Task getExtraction();
    ReadOnlyList<Reward> getRewards();
    ReadOnlyList<SetBack> getSetBacks();

    static String extractFirstStep(FindingExtraction finding) {
        return "* Biome: '" + finding.getBiome().getConfiguration().getDescription() + "'\n" +
                "* Local of Interest: '" + finding.getLocalOfInterest().getDescription() + "'\n" +
                "* Necessary Skills: " + finding.getExtraction().getSkillsRequired() + "\n" +
                "* Investigation: " + finding.getInvestigation().getPresentation() + "\n"
                ;
    }

    static String extractSecondStep(FindingExtraction finding) {
        return "* Extraction: " + finding.getExtraction().getPresentation() + "\n" +
                "* Rewards: " + finding.getRewards() + "\n" +
                "* SetBacks: " + SetBack.print(finding.getSetBacks()) + "\n"
                ;
    }

    static FindingExtraction generateFor(Complex complex) {
        return CommonFindingExtraction.generateFor(complex);
    }
}
