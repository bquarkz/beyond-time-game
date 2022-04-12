package com.intrepid.nicge.utils;

import java.util.Random;

public class RandomUtilz {
    private static final Random RANDOM = new Random(System.currentTimeMillis());

    public static Random getRandom() {
        return RANDOM;
    }

    public static int randomChance() {
        return randomNext(100);
    }

    public static boolean isSorted(float p) {
        return randomChance() < (100 * p);
    }

    public static float randomThreshold(int delta, int threshold) {
        final int m = Math.min(Math.abs(delta), 50);
        final int f = RANDOM.nextBoolean() ? 1 : -1;
        final int r = delta == 0 ? 0 : RANDOM.nextInt(m);
        return (50 + (f * r)) / (float)threshold;
    }

    public static float randomThreshold(int threshold) {
        return randomThreshold(50, threshold);
    }

    public static float randomThreshold() {
        return randomThreshold(50);
    }

    public static int randomNext(int max) {
        return random(1, max);
    }

    public static int random(int min, int max) {
        min = Math.abs(min);
        max = Math.abs(max);
        final int realMin = Math.min(min, max);
        final int realMax = Math.max(min, max);
        final int delta = (realMax - realMin) + 1;
        return realMin + RANDOM.nextInt(delta);
    }
}
