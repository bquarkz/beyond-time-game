package com.intrepid.game.core.resources;

import com.intrepid.game.core.Skills;
import com.intrepid.nicge.utils.readonlycollections.ReadOnlySet;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public enum CommonResources implements Resource {
    METAL_ALLOYS("metal-alloy", "Metal Alloys", 10, 15),
    CHEMICAL_COMPOUNDS("chemical-compounds", "Chemical Compounds", 10, 20),
    ENERGY_CELLS("energy-cells", "Energy Cells", 1, 5),
    ORGANIC_MATTER("organic-matter", "Organic Matter", 30, 50),
    ELECTRONIC_COMPONENTS("electronic-components", "Electronic Components", 5, 10),
    NANOTECHNOLOGY("nanotechnology", "Nanotechnology", 5, 15),
    ;

    private final String code;
    private final String description;
    private final int minNormalQuantity;
    private final int maxNormalQuantity;

    CommonResources(String code, String description, int minNormalQuantity, int maxNormalQuantity) {
        this.code = "resources." + code;
        this.description = description;
        this.minNormalQuantity = minNormalQuantity;
        this.maxNormalQuantity = maxNormalQuantity;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getRandomNormalQuantity(Random random) {
        final int delta = Math.abs(maxNormalQuantity - minNormalQuantity) + 1;
        return minNormalQuantity + random.nextInt(delta) - 1;
    }

    public static ReadOnlySet<Skills> getSkills(CommonResources resource) {
        final Set<Skills> skills = new HashSet<>();
        switch(resource) {
            case METAL_ALLOYS:
            case ENERGY_CELLS:
                skills.add(Skills.ENGINEERING);
                skills.add(Skills.LOGISTIC);
                break;
            case CHEMICAL_COMPOUNDS:
            case ORGANIC_MATTER:
                skills.add(Skills.LOGISTIC);
                break;
            case ELECTRONIC_COMPONENTS:
                skills.add(Skills.ENGINEERING);
                break;
            case NANOTECHNOLOGY:
                skills.add(Skills.ENGINEERING);
                skills.add(Skills.INFILTRATION);
                break;
        }
        return ReadOnlySet.of(skills);
    }

    public static int getFactor(CommonResources resource) {
        int r = 0;
        switch(resource) {
            case ENERGY_CELLS:
                r = 10;
                break;
            case CHEMICAL_COMPOUNDS:
                r = 5;
                break;
            case ORGANIC_MATTER:
                r = 1;
                break;
            case METAL_ALLOYS:
            case ELECTRONIC_COMPONENTS:
                r = 7;
                break;
            case NANOTECHNOLOGY:
                r = 12;
                break;
        }
        return r;
    }
}
