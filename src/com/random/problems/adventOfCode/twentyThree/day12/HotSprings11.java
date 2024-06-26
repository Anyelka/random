package com.random.problems.adventOfCode.twentyThree.day12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class HotSprings11 extends HotSprings1 {

    /**
     * This algorithm generates all the possible spring arrangements (by substituting both '#' and '.' with the unknown
     * '?' characters) and decides for each of them if they fit the given group size criteria.
     *
     * For dummy input it works fine, however it causes a StackOverflowError for the test input.
     * */
    long getPossibleArrangementsForLine(String line) {
        String[] sections = line.split(" ");

        char[] springs = sections[0].toCharArray();
        Long[] damagedSpringGroupSizes = Arrays.stream(sections[1].split(",")).map(Long::parseLong).toArray(Long[]::new);

        long unknownSpringsCount = getUnknownSpringsCount(springs);
        long totalVariations = (long) Math.pow(2, unknownSpringsCount);

        return countFittingArrangements(0, totalVariations, springs, damagedSpringGroupSizes);
    }

    private long getUnknownSpringsCount(char[] springs) {
        return IntStream.range(0, springs.length)
                .mapToObj(i -> springs[i]).filter(character -> character.equals('?'))
                .count();
    }

    private long countFittingArrangements(long i, long totalVariations, char[] springs, Long[] damagedSpringGroupSizes) {
        if (i == totalVariations) {
            return 0;
        }
        char[] currentArrangement = getCurrentArrangement(i, springs);
        return (fitsDamagedSpringGroupSizes(currentArrangement, damagedSpringGroupSizes) ? 1 : 0)
                + countFittingArrangements(i+1, totalVariations, springs, damagedSpringGroupSizes);
    }

    private char[] getCurrentArrangement(long i, char[] springs) {
        char[] arrangement = new char[springs.length];
        int unknownIndex = 1;
        for(int index = 0; index < arrangement.length; index++) {
            if(springs[index] == '?') {
                double currentMax = Math.pow(2, unknownIndex);
                boolean isBroken = (i % currentMax) >= (currentMax / 2);
                arrangement[index] = isBroken ? '#' : '.';
                unknownIndex++;
            } else {
                arrangement[index] = springs[index];
            }
        }
        return arrangement;
    }


    private boolean fitsDamagedSpringGroupSizes(char[] arrangement, Long[] damagedSpringGroupSizes) {
        boolean inDamagedSpringGroup = false;
        List<Long> actualDamagedSpringGroups = new ArrayList<>();
        long currentDamagedSpringGroupLength = 0L;
        for(int i = 0; i < arrangement.length; i++) {
            char spring = arrangement[i];
            if(spring == '#') {
                if (!inDamagedSpringGroup) {
                    inDamagedSpringGroup = true;
                }
                currentDamagedSpringGroupLength++;
            } else if(spring == '.') {
                if(inDamagedSpringGroup) {
                    actualDamagedSpringGroups.add(currentDamagedSpringGroupLength);
                    inDamagedSpringGroup = false;
                    currentDamagedSpringGroupLength = 0L;
                }
            } else {
                throw new RuntimeException(String.format("Invalid spring: {} at index: {}", spring, i));
            }
            if((i == arrangement.length - 1) && currentDamagedSpringGroupLength != 0) {
                actualDamagedSpringGroups.add(currentDamagedSpringGroupLength);
            }
        }
        return actualDamagedSpringGroups.equals(Arrays.asList(damagedSpringGroupSizes));
    }
}
