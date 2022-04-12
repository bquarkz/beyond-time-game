package com.intrepid.game.core.setbacks;

import com.intrepid.game.core.tasks.Task;
import org.apache.commons.lang3.StringUtils;

public class SetBackBuilder {
    private Task mitigation = Task.EMPTY_TASK;
    private String description;
    private String counterMeasure;

    public SetBackBuilder mitigation(Task mitigation) {
        this.mitigation = mitigation;
        return this;
    }

    public SetBackBuilder description(String description) {
        this.description = description;
        return this;
    }

    public SetBackBuilder counterMeasure(String counterMeasure) {
        this.counterMeasure = counterMeasure;
        return this;
    }

    public static SetBackBuilder builder() {
        return new SetBackBuilder();
    }

    public SetBack build() {
        if (StringUtils.isBlank(description)) {
            return SetBack.NOT_SETBACK;
        }

        return new SetBack() {
            @Override
            public Task getMitigation() {
                return mitigation;
            }

            @Override
            public String getDescription() {
                return description;
            }

            @Override
            public String getCounterMeasure() {
                return counterMeasure;
            }
        };
    }
}
