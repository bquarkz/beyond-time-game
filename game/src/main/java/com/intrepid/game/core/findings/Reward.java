package com.intrepid.game.core.findings;


import com.intrepid.game.core.resources.Resource;
import com.intrepid.nicge.utils.readonlycollections.ReadOnlyList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
@AllArgsConstructor
public class Reward {
    private final int quantity;
    private final Resource resource;

    @Override
    public String toString() {
        return "{ " + quantity + "x'" + resource.getDescription() + "' }";
    }

    public boolean hasCode(String code) {
        if (StringUtils.isBlank(code)) return false;
        return code.toLowerCase().equals(resource.getCode());
    }

    public static boolean hasAnyCode(ReadOnlyList<Reward> rewards, String... codes) {
        for (String code : codes) {
            if (rewards.stream().anyMatch(reward -> reward.hasCode(code))) return true;
        }
        return false;
    }

    public static boolean hasAllCodes(ReadOnlyList<Reward> rewards, String... codes) {
        boolean b = true;
        for (String code : codes) {
            b &= rewards.stream().anyMatch(reward -> reward.hasCode(code));
        }
        return b;
    }
}
