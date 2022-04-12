package com.intrepid.game.core.tasks;

import com.intrepid.game.core.Skills;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

abstract class AbstractGenericTaskRequirements implements TaskRequirements {
    private final Map<Skills, SkillRequirement> skillRequirements = new HashMap<>();
    private final List<TaskRequirements> taskRequirements = new ArrayList<>();

    protected AbstractGenericTaskRequirements add(SkillRequirement skillRequirement) {
        if (skillRequirement.getSkill() == null) return this;
        final SkillRequirement sr = skillRequirements.get(skillRequirement.getSkill());
        if (sr != null) {
            final SkillRequirement newRequirement = SkillRequirement
                    .builder()
                    .skill(skillRequirement.getSkill())
                    .value(sr.getTotalSkillsScore() + skillRequirement.getTotalSkillsScore())
                    .build();
            skillRequirements.replace(skillRequirement.getSkill(), newRequirement);
        } else {
            skillRequirements.put(skillRequirement.getSkill(), skillRequirement);
        }

        return this;
    }

    protected AbstractGenericTaskRequirements add(TaskRequirements taskRequirement) {
        taskRequirements.add(taskRequirement);
        return this;
    }

    protected abstract String getJoining();

    @Override
    public Set<Skills> getSkillsRequired() {
        return Stream
                .of(taskRequirements, skillRequirements.values())
                .flatMap(Collection::stream)
                .map(TaskRequirements::getSkillsRequired)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    @Override
    public int getTotalSkillsScore() {
        return Stream
                .of(taskRequirements, skillRequirements.values())
                .flatMap(Collection::stream)
                .map(TaskRequirements::getTotalSkillsScore)
                .reduce(0, Integer::sum);
    }

    @Override
    public String getPresentation() {
        final String joining = " " + getJoining().trim() + " ";
        return String.format(TaskRequirements.TASK_PRESENTATION, Stream
                .of(taskRequirements, skillRequirements.values())
                .flatMap(Collection::stream)
                .map(TaskRequirements::getPresentation)
                .collect(Collectors.joining(joining)));
    }

    @Override
    public String toString() {
        return getPresentation();
    }
}
