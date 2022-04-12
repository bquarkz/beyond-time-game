package com.intrepid.game.core.findings;

import com.intrepid.game.core.Complex;
import com.intrepid.game.core.TreasureRichness;
import com.intrepid.game.core.encounters.Encounter;
import com.intrepid.game.core.resources.CommonResources;
import com.intrepid.game.core.resources.CommonResourcesChances;
import com.intrepid.game.core.biomes.Biomes;
import com.intrepid.game.core.localofinterest.LocalOfInterestCollection;
import com.intrepid.game.core.localofinterest.LocalOfInterest;
import com.intrepid.game.core.tasks.TaskRequirements;
import com.intrepid.nicge.utils.RandomUtilz;
import com.intrepid.nicge.utils.readonlycollections.ReadOnlyList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.intrepid.game.core.resources.CommonResources.*;
import static com.intrepid.nicge.utils.RandomUtilz.isSorted;
import static com.intrepid.nicge.utils.RandomUtilz.randomChance;

class FindingZUtils {
    static LocalOfInterest randomLocalOfInterest(Biomes biome) {
        return LocalOfInterestCollection
                .findByBiome(biome)
                .randomPick(RandomUtilz.getRandom())
                .orElseThrow(() -> new RuntimeException("we should have at least one local of interest available"));
    }

    static ReadOnlyList<Reward> randomCommonReward(Complex complex, LocalOfInterest localOfInterest) {
        final ReadOnlyList<CommonResources> possibleResources = getPossibleCommonResources(complex.getBiome(), localOfInterest);
        final TreasureRichness richness = TreasureRichness
                .stack(localOfInterest.getTreasureRichness(randomChance()), complex.getTreasureRichness());
        final int numberOfRewards = getMaxNumberOfRewards(richness, possibleResources.size());
        final List<Reward> rewards = new ArrayList<>(numberOfRewards);
        for (int i = 0; i < numberOfRewards; i++) {
            final CommonResources resources = possibleResources.get(i);
            final int quantity = (int)(richness.getBalance() * resources.getRandomNormalQuantity(RandomUtilz.getRandom()));
            rewards.add(new Reward(quantity + 1, resources)); // +1 to avoid zero
        }
        return ReadOnlyList.wrap(rewards);
    }

    static int randomInvestigationEffort(TaskRequirements execution, LocalOfInterest localOfInterest, ReadOnlyList<Reward> rewards) {
        final int effortOnExecution = execution.getTotalSkillsScore();
        final int numberOfRewards = rewards.size();
        final int investigationEffort = localOfInterest.getInvestigationEffort(effortOnExecution, numberOfRewards);
        return (int)(investigationEffort * RandomUtilz.randomThreshold(85));
    }


    private static int getMaxNumberOfRewards(TreasureRichness richness, int max) {
        return Math.min(randomNumberOfRewards(), Math.min(richness.getMaxRewards(), max));
    }

    private static int randomNumberOfRewards() {
        final int c = randomChance();
        if (c < 50) {
            return 1;
        } else if (c < 75) {
            return 2;
        } else if (c < 90) {
            return 3;
        } else if (c < 97) {
            return 4;
        } else {
            return 5;
        }
    }

    private static ReadOnlyList<CommonResources> getPossibleCommonResources(Biomes biome, LocalOfInterest localOfInterest) {
        final CommonResourcesChances chances = CommonResourcesChances
                .stack(biome.getConfiguration().getResourceChances(), localOfInterest.getResourceChances());
        final List<CommonResources> possibleResources = new ArrayList<>(values().length);
        if (isSorted(chances.getMetalAlloysChances())) possibleResources.add(METAL_ALLOYS);
        if (isSorted(chances.getChemicalCompoundsChances())) possibleResources.add(CHEMICAL_COMPOUNDS);
        if (isSorted(chances.getEnergyCellsChances())) possibleResources.add(ENERGY_CELLS);
        if (isSorted(chances.getElectronicComponentsChances())) possibleResources.add(ELECTRONIC_COMPONENTS);
        if (isSorted(chances.getOrganicMatterChances())) possibleResources.add(ORGANIC_MATTER);
        if (isSorted(chances.getNanotechnologyChances())) possibleResources.add(NANOTECHNOLOGY);
        Collections.shuffle(possibleResources);
        return ReadOnlyList.wrap(possibleResources);
    }

    public static Encounter randomEncounter(Biomes biome) {
        return null;
    }
}
