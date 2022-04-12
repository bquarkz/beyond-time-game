package com.intrepid.game.core;

public enum TreasureRichness {
    EXTREMELY_POOR(0.35f, 1),
    POOR(0.7f, 2),
    NORMAL(1.0f, 3),
    RICH(1.15f, 4),
    EXTREMELY_RICH(1.35f, 5),
    ;

    private final float balance;
    private final int maxRewards;

    TreasureRichness(float balance, int maxRewards) {
        this.balance = balance;
        this.maxRewards = maxRewards;
    }

    public float getBalance() {
        return balance;
    }

    public int getMaxRewards() {
        return maxRewards;
    }

    public static TreasureRichness stack(TreasureRichness richenessA, TreasureRichness richenessB) {
        final int a = richenessA.ordinal();
        final int b = richenessB.ordinal();
        if (a == b) return richenessA;
        final int m = (a + b) / 2;
        return values()[m];
    }
}
