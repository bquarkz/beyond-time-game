package com.intrepid.game.core.biomes;

public enum Biomes {
    AUTOMATED_FACTORY(new AutomatedFactory()),
    RESEARCH_CENTER(new ResearchCenter()),
    RESIDENTIAL_AREA(new ResidentialArea()),
    ORGANIC_RESERVE(new OrganicReserve()),
    WASTE_TREATMENT(new WasteTreatment()),
    ABANDONED_ZONE(new AbandonedZone()),
    POWER_GENERATOR(new PowerGenerator()),
    CONTROL_CORE(new ControlCore()),
    ;

    private final BiomeConfiguration configuration;

    Biomes(BiomeConfiguration configuration) {
        this.configuration = configuration;
    }

    public BiomeConfiguration getConfiguration() {
        return configuration;
    }
}
