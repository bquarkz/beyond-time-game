package com.intrepid.game.core.tasks;

import com.intrepid.game.core.Skills;

import java.util.Set;

public interface TaskRequirements {
    String TASK_PRESENTATION = "[%s]";
    Set<Skills> getSkillsRequired();
    int getTotalSkillsScore();
    String getPresentation();
}
