package com.random.problems.adventOfCode.twentyThree.day4;

import com.random.problems.adventOfCode.AOCProblem;
import com.random.util.NumberUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Scratchcards1 extends AOCProblem {
  private static final String ROOT = "adventOfCode/2023/day4";

  @Override
  protected String getFolderName() {
    return ROOT;
  }

  @Override
  public String runMethod(String input) {
    List<Card> cards = getCards(input);
    return "" + cards.stream().map(this::getPoints).reduce(0, Integer::sum);
  }

  private List<Card> getCards(String input) {
    String[] rows = input.split("\n");
    return Arrays.stream(rows).map(this::convertToCard).collect(Collectors.toList());
  }

  private Card convertToCard(String row) {
    String[] sections = row.split("\\|");
    return new Card(getNumbers(sections[0]),getNumbers(sections[1]));
  }

  private List<Integer> getNumbers(String section) {
    return Arrays.stream(section.split(" "))
        .filter(NumberUtil::isInt)
        .map(Integer::parseInt)
        .collect(Collectors.toList());
  }

  private int getPoints(Card card) {
    List<Integer> hits = new ArrayList<>(List.copyOf(card.winningNumbers));
    hits.retainAll(card.pickedNumbers);
    return (int) Math.pow(2, hits.size()-1);
  }

  private class Card {
    List<Integer> winningNumbers;
    List<Integer> pickedNumbers;

    Card(List<Integer> winningNumbers, List<Integer> pickedNumbers) {
      this.winningNumbers = winningNumbers;
      this.pickedNumbers = pickedNumbers;
    }
  }
}
