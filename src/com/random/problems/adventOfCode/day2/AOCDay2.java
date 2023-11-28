package com.random.problems.adventOfCode.day2;

import com.random.problems.adventOfCode.AOCProblem;

import java.util.Map;

public class AOCDay2 extends AOCProblem {

    private static class Part1 {
        private static final Map<String, Map<String, Integer>> comparedScoreMap = Map.of(
                "A", Map.of("X", 3, "Y", 6, "Z", 0),
                "B", Map.of("X", 0, "Y", 3, "Z", 6),
                "C", Map.of("X", 6, "Y", 0, "Z", 3)
        );

        private static final Map<String, Integer> inherentScoreMap = Map.of("X", 1, "Y", 2, "Z", 3);

        static int getRoundScore(String[] shapes) {
            return inherentScoreMap.get(shapes[1]) + comparedScoreMap.get(shapes[0]).get(shapes[1]);
        }

    }

    private static class Part2 {
        private static final Map<String, Integer> comparedScoreMap = Map.of("X", 0, "Y", 3, "Z", 6);
        private static final Map<String, Map<String, Integer>> inherentScoreMap = Map.of(
                "A", Map.of("X", 3, "Y", 1, "Z", 2),
                "B", Map.of("X", 1, "Y", 2, "Z", 3),
                "C", Map.of("X", 2, "Y", 3, "Z", 1)
        );

        static int getRoundScore(String[] shapes) {
            return inherentScoreMap.get(shapes[0]).get(shapes[1]) + comparedScoreMap.get(shapes[1]);
        }
    }

    @Override
    public String runMethod(String input) {
        String[] lines = input.split("\r\n");
        int totalScore = 0;
        for(String line: lines) {
            totalScore += Part2.getRoundScore(line.split(" "));
        }
        return "Your total score according to the strategy guide: " + totalScore;
    }

}
