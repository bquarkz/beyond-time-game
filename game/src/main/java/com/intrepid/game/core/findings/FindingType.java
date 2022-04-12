package com.intrepid.game.core.findings;

import com.intrepid.nicge.utils.RandomUtilz;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum FindingType implements Comparator<FindingType> {
    EXTRACTION(95),
    ENCOUNTER(0),
    CONNECTION(0, 4),
    PORTAL(5, 1),
    ;

    private final int probability;
    private final int max;

    FindingType(int probability) {
        this(probability, Integer.MAX_VALUE);
    }

    FindingType(int probability, int max) {
        this.probability = probability;
        this.max = max;
    }

    public int getProbability() {
        return probability;
    }

    public int getMaxNumber() {
        return max;
    }

    @Override
    public int compare(FindingType o1, FindingType o2) {
        return Integer.compare(o1.getProbability(), o2.getProbability());
    }

    public static FindingType getDefault() {
        return EXTRACTION;
    }

    public static FindingType random() {
        final int chance = RandomUtilz.randomChance();
        int lastChance = 0;
        final List<FindingType> sorted = Stream
                .of(FindingType.values())
                .sorted()
                .collect(Collectors.toList());
        for (FindingType type : sorted) {
            final int currentChance = Math.max(0, chance - lastChance);
            if (type.getProbability() > currentChance) {
                return type;
            }
            lastChance += type.getProbability();
        }

        return getDefault();
    }
}
