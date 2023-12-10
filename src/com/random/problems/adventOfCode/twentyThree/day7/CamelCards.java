package com.random.problems.adventOfCode.twentyThree.day7;

import com.random.problems.adventOfCode.AOCProblem;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CamelCards extends AOCProblem {
  private static final String ROOT = "adventOfCode/2023/day7";

  @Override
  protected String getFolderName() {
    return ROOT;
  }

  @Override
  public String runMethod(String input) {
    List<Hand> hands = getHands(input);
    List<Hand> handsSortedByRank = hands.stream().sorted(Hand::sortByRank).collect(Collectors.toList());

    return "" + getTotalWinnings(handsSortedByRank);
  }

  private List<Hand> getHands(String input) {
    return Arrays.stream(input.split("\n")).map(this::getHand).collect(Collectors.toList());
  }

  private Hand getHand(String row) {
    String[] sections = row.split(" ");
    return new Hand(sections[0], Long.parseLong(sections[1]));
  }

  private Long getTotalWinnings(List<Hand> handsSortedByRank) {
    long result = 0;
    for(int i = 0; i < handsSortedByRank.size(); i++) {
      Hand hand = handsSortedByRank.get(i);
      result += hand.bid * (handsSortedByRank.size()-i);
    }
    return result;
  }
}
