package com.intrepid.game.core.tasks;

import com.intrepid.game.core.Skills;
import lombok.Builder;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Builder
@Getter
public class SkillRequirement implements TaskRequirements {
    private final int value;
    private final Skills skill;

    @Override
    public Set<Skills> getSkillsRequired() {
        return Stream.of(skill).collect(Collectors.toSet());
    }

    @Override
    public int getTotalSkillsScore() {
        return value;
    }

    @Override
    public String getPresentation() {
        return String.format("%s(%03d)", skill.name(), value);
    }
}
