package com.intrepid.game.core.findings;

import com.intrepid.game.core.Complex;
import com.intrepid.game.core.Skills;
import com.intrepid.game.core.TestTypes;
import com.intrepid.game.core.biomes.Biomes;
import com.intrepid.game.core.localofinterest.LocalOfInterest;
import com.intrepid.game.core.setbacks.SetBack;
import com.intrepid.game.core.tasks.SkillRequirement;
import com.intrepid.game.core.tasks.Task;
import com.intrepid.game.core.tasks.TaskRequirements;
import com.intrepid.nicge.utils.readonlycollections.ReadOnlyList;
import lombok.Getter;

import static com.intrepid.game.core.findings.FindingZUtils.randomCommonReward;
import static com.intrepid.game.core.findings.FindingZUtils.randomLocalOfInterest;

@Getter
public class CommonFindingExtraction implements FindingExtraction {
    private final Complex complex;
    private LocalOfInterest localOfInterest;
    private final Task extraction = new Task(TestTypes.PROGRESS);
    private final Task investigation = new Task(TestTypes.EFFORT);
    private ReadOnlyList<Reward> rewards;
    private ReadOnlyList<SetBack> setBacks;

    private CommonFindingExtraction(Complex complex) {
        this.complex = complex;
    }

    @Override
    public FindingType getFindingType() {
        return FindingType.EXTRACTION;
    }

    @Override
    public Biomes getBiome() {
        return complex.getBiome();
    }

    private CommonFindingExtraction localOfInterest(LocalOfInterest localOfInterest) {
        this.localOfInterest = localOfInterest;
        return this;
    }

    private CommonFindingExtraction extractionTask(TaskRequirements task) {
        extraction.addTask(task);
        return this;
    }

    private CommonFindingExtraction investigationEffort(int effort) {
        investigation.addTask(SkillRequirement.builder().skill(Skills.EXPLORATION).value(effort).build());
        return this;
    }

    private CommonFindingExtraction reward(ReadOnlyList<Reward> rewards) {
        this.rewards = rewards;
        return this;
    }

    private CommonFindingExtraction setBacks(ReadOnlyList<SetBack> setBacks) {
        this.setBacks = setBacks;
        return this;
    }

    public static CommonFindingExtraction generateFor(Complex complex) {
        final LocalOfInterest localOfInterest = randomLocalOfInterest(complex.getBiome());
        final ReadOnlyList<Reward> rewards = randomCommonReward(complex, localOfInterest);
        final TaskRequirements extraction = localOfInterest.getExtraction(rewards);
        final int effort = FindingZUtils.randomInvestigationEffort(extraction, localOfInterest, rewards);
        final ReadOnlyList<SetBack> setBacks = localOfInterest.getSetBacks(complex, rewards);

        return new CommonFindingExtraction(complex)
                .localOfInterest(localOfInterest)
                .reward(rewards)
                .extractionTask(extraction)
                .investigationEffort(effort)
                .setBacks(setBacks);
    }

    @Override
    public String toString() {
        return "ResourceFinding: \n" +
                "* Biome: '" + getBiome().getConfiguration().getDescription() + "'\n" +
                "* Local of Interest: '" + localOfInterest.getDescription() + "'\n" +
                "* Extraction: " + extraction + "\n" +
                "* Investigation: " + investigation + "\n" +
                "* Rewards: " + rewards + "\n" +
                "* SetBacks: " + SetBack.print(setBacks) + "\n" +
                "------------------\n";
    }
}

