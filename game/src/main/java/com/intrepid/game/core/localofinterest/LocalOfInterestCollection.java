package com.intrepid.game.core.localofinterest;

import com.intrepid.game.core.Complex;
import com.intrepid.game.core.TreasureRichness;
import com.intrepid.game.core.biomes.Biomes;
import com.intrepid.game.core.findings.Reward;
import com.intrepid.game.core.localofinterest.impl.BrokenPortal;
import com.intrepid.game.core.localofinterest.impl.DisabledLines;
import com.intrepid.game.core.resources.CommonResourcesChances;
import com.intrepid.game.core.setbacks.SetBack;
import com.intrepid.game.core.tasks.TaskRequirements;
import com.intrepid.nicge.utils.readonlycollections.ReadOnlyList;
import com.intrepid.nicge.utils.readonlycollections.ReadOnlySet;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum LocalOfInterestCollection implements LocalOfInterest {
    DISABLED_LINES(new DisabledLines()),
    BROKEN_PORTAL(new BrokenPortal()),
    ;

    private final LocalOfInterest localOfInterest;

    LocalOfInterestCollection(BaseLocalOfInterest localOfInterest) {
        this.localOfInterest = localOfInterest;
        try {
            final Field field = this.localOfInterest
                    .getClass()
                    .getSuperclass()
                    .getDeclaredField("localOfInterest");
            field.setAccessible(true);
            field.set(this.localOfInterest, this);
            field.setAccessible(false);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        localOfInterest.getBiomes().forEach(b -> Inner.localOfInterestByBiomes.get(b).add(this));
    }

    public String getDescription() {
        return localOfInterest.getDescription();
    }

    @Override
    public ReadOnlySet<Biomes> getBiomes() {
        return localOfInterest.getBiomes();
    }


    @Override
    public CommonResourcesChances getResourceChances() {
        return localOfInterest.getResourceChances();
    }

    @Override
    public TreasureRichness getTreasureRichness(int chance) {
        return localOfInterest.getTreasureRichness(chance);
    }

    @Override
    public int getInvestigationEffort(int effortOnExtraction, int numberOfRewards) {
        return localOfInterest.getInvestigationEffort(effortOnExtraction, numberOfRewards);
    }

    @Override
    public TaskRequirements getExtraction(ReadOnlyList<Reward> rewards) {
        return localOfInterest.getExtraction(rewards);
    }

    @Override
    public ReadOnlyList<SetBack> getSetBacks(Complex complex, ReadOnlyList<Reward> rewards) {
        return localOfInterest.getSetBacks(complex, rewards);
    }

    private static class Inner {
        private static final Map<Biomes, List<LocalOfInterest>> localOfInterestByBiomes = new HashMap<>();
        static {
            for (Biomes biome : Biomes.values()) {
                localOfInterestByBiomes.put(biome, new ArrayList<>());
            }
        }
    }

    public static ReadOnlyList<LocalOfInterest> findByBiome(Biomes biome) {
        return ReadOnlyList.wrap(Inner.localOfInterestByBiomes.get(biome));
    }
}
