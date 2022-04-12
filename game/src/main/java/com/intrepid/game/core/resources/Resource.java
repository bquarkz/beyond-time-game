package com.intrepid.game.core.resources;

import java.util.Random;

public interface Resource {
    String getCode();
    String getDescription();
    int getRandomNormalQuantity(Random random);
}
