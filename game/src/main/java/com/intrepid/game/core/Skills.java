package com.intrepid.game.core;

import com.intrepid.game.core.tasks.SkillRequirement;

public enum Skills {
    COMBAT,
    EXPLORATION,
    INFILTRATION,
    LOGISTIC,
    ENGINEERING,
    ;

    public SkillRequirement asRequirement(int value) {
        return SkillRequirement.builder().skill(this).value(value).build();
    }
}
