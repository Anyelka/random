package com.random.problems.adventOfCode.twentyThree.day4;

import com.random.problems.adventOfCode.AOCProblem;
import com.random.util.NumberUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Scratchcards2 extends AOCProblem {
  private static final String ROOT = "adventOfCode/2023/day4";

  @Override
  protected String getFolderName() {
    return ROOT;
  }

  @Override
  public String runMethod(String input) {
    List<Card> cards = getCards(input);
    return "" + getTotalCardNumber(cards);
  }

  private int getTotalCardNumber(List<Card> cards) {
    for(int i = 0; i < cards.size(); i++) {
      Card current = cards.get(i);
      int hits = current.getHits();

      for(int j = 0; j < current.size; j++) {

        for(int k = 1; k <= hits; k++) {
          if(i+k <= cards.size()){
            Card nextCard = cards.get(i + k);
            nextCard.addOne();
          }
        }

      }
    }
    return cards.stream().map(Card::getSize).reduce(0, Integer::sum);
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

  private class Card {
    int size;
    List<Integer> winningNumbers;
    List<Integer> pickedNumbers;

    Card(List<Integer> winningNumbers, List<Integer> pickedNumbers) {
      this.size = 1;
      this.winningNumbers = winningNumbers;
      this.pickedNumbers = pickedNumbers;
    }

    private int getHits() {
      List<Integer> hits = new ArrayList<>(List.copyOf(this.winningNumbers));
      hits.retainAll(this.pickedNumbers);
      return hits.size();
    }

    public void addOne() {
      this.size++;
    }

    public int getSize() {
      return this.size;
    }
  }
}
