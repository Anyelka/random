package com.random.problems.hackerrank.climbingTheLeaderboard;

import com.random.problems.AbstractProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClimbingTheLeaderboard extends AbstractProblem {
    private static final String ROOT = "hackerrank/climbingTheLeaderboard";

    @Override
    public String runMethod(List<String> lines) {
        List<Integer> leaderboard = Arrays.stream(lines.get(1).split(" ")).map(Integer::valueOf).collect(Collectors.toList());
        List<Integer> scores = Arrays.stream(lines.get(3).split(" ")).map(Integer::valueOf).collect(Collectors.toList());;
        List<Integer> rankings = climbingLeaderboard(leaderboard, scores);
        return rankings.stream().map(Object::toString).collect(Collectors.joining(","));
    }

    public List<Integer> climbingLeaderboard(List<Integer> ranked, List<Integer> player) {
        return player.stream().map(roundScore -> this.rankRoundScore(ranked, roundScore)).collect(Collectors.toUnmodifiableList());
    }

    private Integer rankRoundScore(List<Integer> ranked, Integer roundScore) {
        System.out.println("Calculating ranking for score: " + roundScore);
        return getRanking(0, 1, ranked, roundScore);
    }

    private Integer getRanking(int i, int rank, List<Integer> ranked, Integer roundScore) {
        if(i >= ranked.size()) {
            System.out.println("Rank of: " + roundScore + " is:" + rank);
            return rank;
        }
        Integer current = ranked.get(i);
        if(roundScore >= current) {
            System.out.println("Rank of: " + roundScore + " is:" + rank);
            return rank;
        }

        int nextRank = (ranked.size() > i+1 && ranked.get(i + 1).equals(current)) ? rank : rank + 1;
        System.out.println("next index: " + i + ", next rank: " + nextRank);
        return getRanking(i+1, nextRank, ranked, roundScore);
    }

    @Override
    public String getExpectedResult(List<String> lines) {
        return getContentAfterEmptyLine(lines);
    }

    private String getContentAfterEmptyLine(List<String> lines) {
        return getContentAfterOrBeforeEmptyLine(lines, false);
    }

    private String getContentAfterOrBeforeEmptyLine(List<String> lines, boolean isBeforeNeeded) {
        boolean isResult = isBeforeNeeded;
        List<Integer> results = new ArrayList<>();
        for(String line: lines) {
            if(isResult) {
                results.add(Integer.parseInt(line));
            }
            if(line.isEmpty()) {
                isResult = !isResult;
            }
        }
        return results.stream().map(Object::toString).collect(Collectors.joining(","));
    }

    @Override
    protected String getFolderName() {
        return ROOT;
    }
}
