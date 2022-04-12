package com.intrepid.game.core.resources;

public interface CommonResourcesChances {
    float getMetalAlloysChances();
    float getChemicalCompoundsChances();
    float getEnergyCellsChances();
    float getOrganicMatterChances();
    float getElectronicComponentsChances();
    float getNanotechnologyChances();

    static CommonResourcesChances none() {
        return same(0);
    }

    static CommonResourcesChances allFull() {
        return same(1);
    }

    static CommonResourcesChances allHalf() {
        return same(.5);
    }

    static CommonResourcesChances same(double value) {
        return with(value, value, value, value, value, value);
    }

    static CommonResourcesChances with(
            final double metalAlloy,
            final double chemicalCompounds,
            final double energyCells,
            final double organicMatter,
            final double electronicComponents,
            final double nanotechnology) {
        return with((float)metalAlloy,
                (float)chemicalCompounds,
                (float)energyCells,
                (float)organicMatter,
                (float)electronicComponents,
                (float)nanotechnology);
    }

        static CommonResourcesChances with(
            final float metalAlloy,
            final float chemicalCompounds,
            final float energyCells,
            final float organicMatter,
            final float electronicComponents,
            final float nanotechnology) {
        return new CommonResourcesChances() {
            @Override
            public float getMetalAlloysChances() {
                return metalAlloy;
            }

            @Override
            public float getChemicalCompoundsChances() {
                return chemicalCompounds;
            }

            @Override
            public float getEnergyCellsChances() {
                return energyCells;
            }

            @Override
            public float getOrganicMatterChances() {
                return organicMatter;
            }

            @Override
            public float getElectronicComponentsChances() {
                return electronicComponents;
            }

            @Override
            public float getNanotechnologyChances() {
                return nanotechnology;
            }
        };
    }

    static CommonResourcesChances stack(CommonResourcesChances... chances) {
        float metalAlloy = 1.0f;
        float chemicalCompounds = 1.0f;
        float energyCells = 1.0f;
        float organicMatter = 1.0f;
        float electronicComponents = 1.0f;
        float nanotechnology = 1.0f;

        for (CommonResourcesChances chance : chances) {
            metalAlloy *= chance.getMetalAlloysChances();
            chemicalCompounds *= chance.getChemicalCompoundsChances();
            energyCells *= chance.getEnergyCellsChances();
            organicMatter *= chance.getOrganicMatterChances();
            electronicComponents *= chance.getElectronicComponentsChances();
            nanotechnology *= chance.getNanotechnologyChances();
        }

        return with(metalAlloy, chemicalCompounds, energyCells, organicMatter, electronicComponents, nanotechnology);
    }
}
