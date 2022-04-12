package com.intrepid.game.core.findings;

import java.util.Collections;
import java.util.LinkedList;

public class DeckOfFindings {
    private final LinkedList<Finding> findings = new LinkedList<>();

    public void shuffle() {
        Collections.shuffle(findings);
    }

    public void push(Finding finding) {
        findings.add(finding);
    }

    public Finding pop() {
        return findings.pop();
    }

    public boolean isEmpty() {
        return findings.isEmpty();
    }
}
