package com.random.problems.adventOfCode.twentyThree.day7;

import com.random.problems.adventOfCode.AOCProblem;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public abstract class CamelCards extends AOCProblem {
  private static final String ROOT = "adventOfCode/2023/day7";

  @Override
  protected String getFolderName() {
    return ROOT;
  }

  @Override
  public String runMethod(String input) {
    List<Hand> hands = getHands(input);
    List<Hand> handsSortedByRank = hands.stream().sorted(sortHandComparator()).collect(Collectors.toList());

    return "" + getTotalWinnings(handsSortedByRank);
  }

  private List<Hand> getHands(String input) {
    return Arrays.stream(input.split("\n")).map(this::getHand).collect(Collectors.toList());
  }

  private Hand getHand(String row) {
    String[] sections = row.split(" ");
    return getHand(sections);
  }

  private Long getTotalWinnings(List<Hand> handsSortedByRank) {
    long result = 0;
    for(int i = 0; i < handsSortedByRank.size(); i++) {
      Hand hand = handsSortedByRank.get(i);
      result += hand.bid * (handsSortedByRank.size()-i);
    }
    return result;
  }

  abstract Comparator<Hand> sortHandComparator();

  abstract Hand getHand(String[] sections);
}
