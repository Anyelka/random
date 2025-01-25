package com.random.problems.adventOfCode.twentyTwo.day3;

import com.random.problems.adventOfCode.AOCProblem;

public class AOCDay3 extends AOCProblem {
    private final static String TEST_INPUT = "vJrwpWtwJgWrhcsFMMfFFhFp\n" +
            "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL\n" +
            "PmmdzqPrVvPwwTWBwg\n" +
            "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn\n" +
            "ttgJtRGJQctTZtZT\n" +
            "CrZsJsPPZsGzwwsLwLmpwMDw";
    public String runTestMethod(String input) {
        return runMethod(TEST_INPUT);
    }

    @Override
    public String runMethod(String input) {
        String[] lines = input.split("\n");
        int totalPriorities;
        for(String line: lines) {
            char[] chars = line.toCharArray();
            StringBuilder firstComp = new StringBuilder();
            StringBuilder secondComp = new StringBuilder();
            for(int i = 0; i < chars.length; i++) {
                if(i < chars.length/2) {
                    firstComp.append(chars[i]);
                } else {
                    secondComp.append(chars[i]);
                }
            }

        }
        //TODO:implement
        return "?";
    }

    @Override
    protected String getFolderName() {
        return "adventOfCode/2022/day3";
    }
}
