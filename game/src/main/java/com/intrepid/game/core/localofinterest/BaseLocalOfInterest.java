package com.intrepid.game.core.localofinterest;

import com.intrepid.game.core.Complex;
import com.intrepid.game.core.Skills;
import com.intrepid.game.core.TreasureRichness;
import com.intrepid.game.core.biomes.Biomes;
import com.intrepid.game.core.findings.Reward;
import com.intrepid.game.core.resources.CommonResources;
import com.intrepid.game.core.resources.CommonResourcesChances;
import com.intrepid.game.core.setbacks.SetBack;
import com.intrepid.game.core.setbacks.SetBackTemplate;
import com.intrepid.game.core.setbacks.SetBackTemplateCollection;
import com.intrepid.game.core.tasks.AllTaskRequirements;
import com.intrepid.game.core.tasks.TaskRequirements;
import com.intrepid.nicge.utils.RandomUtilz;
import com.intrepid.nicge.utils.readonlycollections.ReadOnlyList;
import com.intrepid.nicge.utils.readonlycollections.ReadOnlySet;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.intrepid.nicge.utils.RandomUtilz.randomThreshold;

public abstract class BaseLocalOfInterest implements LocalOfInterest {
    private LocalOfInterestCollection localOfInterest;

    public abstract String getDescription();
    public abstract ReadOnlySet<Biomes> getBiomes();

    public final LocalOfInterestCollection getLocalOfInterest() {
        return localOfInterest;
    }

    public CommonResourcesChances getResourceChances() {
        return CommonResourcesChances.allHalf();
    }

    public TreasureRichness getTreasureRichness(int chance) {
        return TreasureRichness.NORMAL;
    }

    protected int getMaxNumberOfSetBacks() {
        return 3;
    }

    protected int getChanceForNoSetBacks() {
        return 5;
    }

    @Override
    public int getInvestigationEffort(int effortOnExtraction, int numberOfRewards) {
        return (effortOnExtraction / 10) + numberOfRewards;
    }

    @Override
    public TaskRequirements getExtraction(ReadOnlyList<Reward> rewards) {
        final AllTaskRequirements allTaskRequirements = new AllTaskRequirements();
        for (Reward reward : rewards) {
            if (reward.getResource() instanceof CommonResources) {
                final CommonResources resource = (CommonResources) reward.getResource();
                final ReadOnlySet<Skills> skills = CommonResources.getSkills(resource);
                for (Skills skill : skills) {
                    final int quantity = reward.getQuantity();
                    final int factor = (int)(CommonResources.getFactor(resource) * randomThreshold());
                    allTaskRequirements.add(skill.asRequirement(quantity * (factor == 0 ? 1 : factor)));
                }
            }
        }
        return allTaskRequirements;
    }

    private int getNumberOfSetBacks(int numberOfSetBacks, ReadOnlyList<Reward> rewards) {
        final int numberOfRewards = rewards.size();
        final int maxNumberOfSetBacks = Math.min(numberOfRewards, getMaxNumberOfSetBacks());
        return Math.min(maxNumberOfSetBacks, RandomUtilz.randomNext(numberOfSetBacks));
    }

    public ReadOnlyList<SetBack> getSetBacks(Complex complex, ReadOnlyList<Reward> rewards) {
        final int chanceForNoSetBacks = Math
                .max(complex.getBiome().getConfiguration().getChanceForNoSetBacks(), getChanceForNoSetBacks());
        if (RandomUtilz.randomChance() <= chanceForNoSetBacks) {
            return ReadOnlyList.empty();
        }

        final List<SetBackTemplate> setBackTemplates = SetBackTemplateCollection
                .findByLocalOfInterest(getLocalOfInterest())
                .stream()
                .collect(Collectors.toList());
        Collections.shuffle(setBackTemplates);
        return ReadOnlyList.wrap(setBackTemplates
                .stream()
                .limit(getNumberOfSetBacks(setBackTemplates.size(), rewards))
                .map(template -> template.build(rewards))
                .filter(setBack -> setBack != SetBack.NOT_SETBACK)
                .collect(Collectors.toList()));
    }
}
