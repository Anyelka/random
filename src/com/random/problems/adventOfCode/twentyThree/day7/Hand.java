package com.random.problems.adventOfCode.twentyThree.day7;

import java.util.function.BiFunction;

public class Hand {
  HandType handType;
  char[] cards;
  Long bid;

  private Hand(String cards, HandType handType, Long bid) {
    this.handType = handType;
    this.cards = cards.toCharArray();
    this.bid = bid;
  }

  public static Hand of1(String cards, String bid) {
    return new Hand(cards, HandType.getHandType1(cards), Long.parseLong(bid));
  }

  public static Hand of2(String cards, String bid) {
    return new Hand(cards, HandType.getHandType2(cards), Long.parseLong(bid));
  }

  public int sortByRank1(Hand otherHand) {
    int rankDifference = this.getHandTypeRank() - otherHand.getHandTypeRank();
    return rankDifference != 0 ? rankDifference : getHighCardRankDifference(this.cards, otherHand.cards);
  }

  public int sortByRank2(Hand otherHand) {
    int rankDifference = this.getHandTypeRank() - otherHand.getHandTypeRank();
    return rankDifference != 0 ? rankDifference : getHighCardRankDifferenceWithJokers(this.cards, otherHand.cards);
  }

  private int getHighCardRankDifference(char[] cards, char[] otherCards) {
    int rankDifference = - CardType.getRank(cards[0]) + CardType.getRank(otherCards[0]);
    return rankDifference != 0 ? rankDifference : getHighCardRankDifference(removeFirst(cards), removeFirst(otherCards));
  }

  private int getHighCardRankDifferenceWithJokers(char[] cards, char[] otherCards) {
    int rankDifference = - CardType.getRankWithJokers(cards[0]) + CardType.getRankWithJokers(otherCards[0]);
    return rankDifference != 0 ? rankDifference : getHighCardRankDifferenceWithJokers(removeFirst(cards), removeFirst(otherCards));
  }

  private int getHandTypeRank() {
    return handType.rank;
  }

  private char[] removeFirst(char[] array) {
    return new String(array).substring(1).toCharArray();
  }
}
