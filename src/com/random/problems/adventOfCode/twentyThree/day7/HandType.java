package com.random.problems.adventOfCode.twentyThree.day7;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

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

  static HandType getHandType1(String hand) {
    return getHandType(hand, null);
  }

  public static HandType getHandType2(String hand) {
    return getHandType(hand, transformJs());
  }

  public static HandType getHandType(String hand, Function<Map<Integer, Integer>, Map<Integer, Integer>> transformFunction) {
    Map<Integer, Integer> cardRanksWithCount = getCardRankMap(hand.toCharArray());
    if(transformFunction != null) {
      cardRanksWithCount = transformFunction.apply(cardRanksWithCount);
    }
    return getHandType(cardRanksWithCount);
  }

  private static HandType getHandType(Map<Integer, Integer> cardRanksWithCount) {
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
    return sortByRank(cardRanksWithCount);
  }

    private static Function<Map<Integer, Integer>, Map<Integer, Integer>> transformJs() {
    return map -> {
      Map<Integer, Integer> resultMap = sortByCount(map);
      Map.Entry<Integer, Integer> jumboEntry = resultMap.entrySet().stream().filter(entry -> entry.getKey() == 11).findFirst().orElse(null);
      if (jumboEntry == null) {
        return map;
      } else {
        Map.Entry<Integer, Integer> highestRankEntry = resultMap.entrySet().stream().filter(entry -> entry.getKey() != 11).findFirst().orElse(null);
        if(highestRankEntry == null) {
          return map;
        }
        resultMap.remove(jumboEntry.getKey());
        resultMap.put(highestRankEntry.getKey(), highestRankEntry.getValue() + jumboEntry.getValue());
        return sortByRank(resultMap);
      }
    };
  }

  private static Map<Integer, Integer> sortByCount(Map<Integer, Integer> map) {
    return sortBy(map, compareByCount());
  }

  private static Map<Integer, Integer> sortByRank(Map<Integer, Integer> map) {
    return sortBy(map, compareByRank());
  }

  private static Map<Integer, Integer> sortBy(Map<Integer, Integer> map, Comparator<Map.Entry<Integer, Integer>> comparator) {
    return map.entrySet().stream()
        .sorted(comparator)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (v1, v2)->v1, LinkedHashMap::new));
  }

  private static Comparator<Map.Entry<Integer, Integer>> compareByCount() {
    return getComparator(Map.Entry::getValue, true);
  }

  private static Comparator<Map.Entry<Integer, Integer>> compareByRank() {
    return getComparator(Map.Entry::getKey, false);
  }

  private static Comparator<Map.Entry<Integer, Integer>> getComparator(ToIntFunction<Map.Entry<Integer, Integer>> comparing, boolean isReversed) {
    Comparator<Map.Entry<Integer, Integer>> comparator = Comparator.comparingInt(comparing);
    return isReversed ? comparator.reversed() : comparator;
  }
}
