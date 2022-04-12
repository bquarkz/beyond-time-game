package com.intrepid.game.core.tasks;

public class AnyTaskRequirements extends AbstractGenericTaskRequirements {
    @Override
    protected String getJoining() {
        return "or";
    }

    public AnyTaskRequirements add(SkillRequirement skillRequirement) {
        return (AnyTaskRequirements) super.add(skillRequirement);
    }

    public AnyTaskRequirements add(TaskRequirements taskRequirement) {
        return (AnyTaskRequirements) super.add(taskRequirement);
    }
}
