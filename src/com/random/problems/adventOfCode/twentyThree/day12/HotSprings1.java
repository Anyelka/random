package com.random.problems.adventOfCode.twentyThree.day12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public abstract class HotSprings1 extends HotSprings{

    public static final char DAMAGED_SPRING = '#';

    @Override
    long getPossibleArrangements(String input) {
        return Arrays.stream(input.split("\n"))
                .map(this::getPossibleArrangementsForLine)
                .reduce(0L, Long::sum);
    }

    abstract long getPossibleArrangementsForLine(String line);

}
