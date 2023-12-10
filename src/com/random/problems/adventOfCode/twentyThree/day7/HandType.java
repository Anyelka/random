package com.random.problems.adventOfCode.twentyThree.day7;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public enum HandType {
  FIVE_OF_A_KIND(1),
  FOUR_OF_A_KIND(2),
  FULL_HOUSE(3),
  THREE_OF_A_KIND(4),
  TWO_PAIR(5),
  ONE_PAIR(6),
  HIGH_CARD(7);

  final int rank;

  HandType(int rank) {
    this.rank = rank;
  }

  static Stream<Character> charStream(char[] charArray) {
    return IntStream.range(0, charArray.length).mapToObj(i -> charArray[i]);
  }


  static HandType getHandType(String hand) {
    char[] cards = hand.toCharArray();
    Map<Integer, Integer> cardRanksWithCount = getCardRankMap(cards);

    if(cardRanksWithCount.size()==1) {
      return HandType.FIVE_OF_A_KIND;
    }
    Map.Entry<Integer, Integer> firstEntry = cardRanksWithCount.entrySet().stream().findFirst().get();
    if(cardRanksWithCount.size()==2) {
      if(firstEntry.getValue() == 1 || firstEntry.getValue() == 4) {
        return HandType.FOUR_OF_A_KIND;
      }
      return HandType.FULL_HOUSE;
    }
    if(cardRanksWithCount.size()==3) {

      Optional<Map.Entry<Integer, Integer>> threeOfAKind =
          cardRanksWithCount.entrySet().stream().filter(entry -> entry.getValue() == 3).findFirst();
      if(threeOfAKind.isPresent()) {
        return HandType.THREE_OF_A_KIND;
      }
      return HandType.TWO_PAIR;
    }
    if(cardRanksWithCount.size() == 4) {
      return HandType.ONE_PAIR;
    }
    return HandType.HIGH_CARD;
  }

  private static Map<Integer, Integer> getCardRankMap(char[] cards) {
    Map<Integer, Integer> cardRanksWithCount = new HashMap<>();
    for(char card : cards) {
      int rank = CardType.getRank(card);
      Integer prevCount = cardRanksWithCount.get(rank);
      if(prevCount != null) {
        cardRanksWithCount.replace(rank, prevCount+1);
      } else {
        cardRanksWithCount.put(rank, 1);
      }
    }
    return cardRanksWithCount.entrySet().stream()
        .sorted(Comparator.comparingInt(Map.Entry::getKey))
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2)->v1, LinkedHashMap::new));
  }
}
