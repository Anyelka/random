package com.random.problems.adventOfCode.twentyThree.day12;

import com.random.problems.adventOfCode.AOCProblem;

public abstract class HotSprings extends AOCProblem {
    private static final String ROOT = "adventOfCode/2023/day12";

    @Override
    protected String getFolderName() {
        return ROOT;
    }

    @Override
    public String runMethod(String input) {
        return "" + getPossibleArrangements(input);
    }

    abstract long getPossibleArrangements(String input);
}
