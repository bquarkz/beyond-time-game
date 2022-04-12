package com.intrepid.game.core.tasks;

public class AllTaskRequirements extends AbstractGenericTaskRequirements {
    @Override
    protected String getJoining() {
        return "and";
    }

    public AllTaskRequirements add(SkillRequirement skillRequirement) {
        return (AllTaskRequirements) super.add(skillRequirement);
    }

    public AllTaskRequirements add(TaskRequirements taskRequirement) {
        return (AllTaskRequirements) super.add(taskRequirement);
    }
}
