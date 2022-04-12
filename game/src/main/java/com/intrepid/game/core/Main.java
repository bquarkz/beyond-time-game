package com.intrepid.game.core;

import com.intrepid.game.core.biomes.Biomes;
import com.intrepid.game.core.findings.Finding;
import com.intrepid.nicge.utils.readonlycollections.ReadOnlyList;

public class Main {
    public static void main(String[] args) {
        final Complex complex = new Complex(Biomes.ABANDONED_ZONE, TreasureRichness.NORMAL);
        while(complex.hasNext()) {
            final Finding finding = complex.next();
            final ReadOnlyList<String> steps = Finding.processSteps(finding);

            int counter = 1;
            for (String step : steps) {
                System.out.printf("%02d) ----------------\n%s%n", counter++, step);
            }
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
        }
    }
}
