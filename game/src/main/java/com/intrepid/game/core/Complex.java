package com.intrepid.game.core;

import com.intrepid.game.core.biomes.Biomes;
import com.intrepid.game.core.findings.*;
import com.intrepid.nicge.utils.RandomUtilz;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Complex implements Iterator<Finding> {
    private final Biomes biome;
    private final TreasureRichness treasureRichness;
    private final DeckOfFindings deckOfFindings = new DeckOfFindings();

    public Complex(Biomes biome, TreasureRichness treasureRichness) {
        this.biome = biome;
        this.treasureRichness = treasureRichness;
        populateFindingDeck();
    }

    public Biomes getBiome() {
        return biome;
    }

    public TreasureRichness getTreasureRichness() {
        return treasureRichness;
    }

    private int getNumberOfFindingsOnDeck() {
        final int delta = (treasureRichness.ordinal() + 1) * 2;
        final int min = delta + 4;
        final int max = min + delta;
        return RandomUtilz.random(min, max);
    }

    private void populateFindingDeck() {
        final Map<FindingType, AtomicInteger> map = new HashMap<>();
        final int numberOfFindingsOnDeck = getNumberOfFindingsOnDeck();
        for (int i = 0; i < numberOfFindingsOnDeck; i++) {
            final Finding finding = Finding.randomGenerateFor(this);
            AtomicInteger currentNumberOfType = map.get(finding.getFindingType());
            if (currentNumberOfType == null) {
                currentNumberOfType = new AtomicInteger(0);
                map.put(finding.getFindingType(), currentNumberOfType);
            }
            if (currentNumberOfType.get() < finding.getFindingType().getMaxNumber()) {
                currentNumberOfType.getAndIncrement();
                deckOfFindings.push(finding);
            } else {
                i--;
            }
        }
        deckOfFindings.shuffle();
    }

    @Override
    public boolean hasNext() {
        return !deckOfFindings.isEmpty();
    }

    @Override
    public Finding next() {
        return deckOfFindings.pop();
    }
}
