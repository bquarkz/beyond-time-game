package com.intrepid.game.core.setbacks;

import com.intrepid.game.core.tasks.Task;
import com.intrepid.nicge.utils.readonlycollections.ReadOnlyList;
import org.apache.commons.lang3.StringUtils;

public interface SetBack {
    SetBack NOT_SETBACK = new SetBack() {
        @Override
        public Task getMitigation() {
            return null;
        }

        @Override
        public String getDescription() {
            return "no Setback";
        }

        @Override
        public String getCounterMeasure() {
            return null;
        }
    };

    Task getMitigation();
    String getDescription();
    String getCounterMeasure();

    static String print(SetBack setBack) {
        final StringBuilder sb = new StringBuilder("{\n");
        sb.append("\t- Description: '").append(setBack.getDescription()).append("'\n");
        if (setBack.getMitigation() != null) {
            sb.append("\t- Mitigation: ");
            if (setBack.getMitigation() != Task.EMPTY_TASK) {
                sb.append(setBack.getMitigation());
            } else {
                sb.append("[NO MITIGATION] 'it will start as soon extraction starts'");
            }
            sb.append("\n");
        }
        if (StringUtils.isNotBlank(setBack.getCounterMeasure())) {
            sb.append("\t- Counter Measure: '").append(setBack.getCounterMeasure()).append("'\n");
        }
        sb.append("}");
        return  sb.toString();
    }

    static String print(ReadOnlyList<SetBack> setBacks) {
        if (setBacks.isEmpty()) {
            return "--";
        }

        final StringBuilder sb = new StringBuilder("\n");
        for (SetBack setBack : setBacks) {
            sb.append(SetBack.print(setBack)).append(",");
        }
        return sb.toString();
    }
}
