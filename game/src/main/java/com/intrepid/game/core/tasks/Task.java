package com.intrepid.game.core.tasks;

import com.intrepid.game.core.Skills;
import com.intrepid.game.core.TestTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Task implements TaskRequirements {
    public static final Task EMPTY_TASK = new Task(null);

    private final TestTypes testType;
    private final List<TaskRequirements> tasks = new ArrayList<>();
    private String description = "";

    public Task(TestTypes testType) {
        this.testType = testType;
    }

    public Task setDescription(String description) {
        if (testType == null) {
            return this;
        }
        this.description = description;
        return this;
    }

    public Task addTask(TaskRequirements task) {
        if (testType == null) {
            return this;
        }
        tasks.add(task);
        return this;
    }

    public Task asInterchangeablyTest() {
        setDescription("Any skill could be used interchangeably");
        return this;
    }

    @Override
    public Set<Skills> getSkillsRequired() {
        return tasks
                .stream()
                .map(TaskRequirements::getSkillsRequired)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    @Override
    public int getTotalSkillsScore() {
        return tasks
                .stream()
                .map(TaskRequirements::getTotalSkillsScore)
                .reduce(0, Integer::sum);
    }

    @Override
    public String getPresentation() {
        if (testType == null) {
            return "no task";
        }

        if (tasks.isEmpty()) {
            return "-";
        }

        final String steps;
        if (tasks.size() == 1) {
            steps = tasks.get(0).getPresentation();
        } else {
            int step = 1;
            final StringBuilder sb = new StringBuilder("\n");
            for (TaskRequirements task : tasks) {
                sb.append("\t").append(step++).append(") ").append(task.getPresentation()).append("\n");
            }
            steps = sb.toString();
        }
        if (description == null || description.isEmpty()) {
            return String.format("tests of %s against: %s", testType.name(), steps);
        } else {
            return String.format("tests of %s against: %s :: %s", testType.name(), steps, description);
        }
    }

    @Override
    public String toString() {
        return getPresentation();
    }

}
