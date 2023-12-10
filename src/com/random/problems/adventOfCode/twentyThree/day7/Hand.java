package com.random.problems.adventOfCode.twentyThree.day7;

public class Hand {
  HandType handType;
  char[] cards;
  Long bid;

  public Hand(String cards, Long bid) {
    this.handType = HandType.getHandType(cards);
    this.cards = cards.toCharArray();
    this.bid = bid;
  }

  public int sortByRank(Hand otherHand) {
    int rankDifference = this.getHandTypeRank() - otherHand.getHandTypeRank();
    return rankDifference != 0 ? rankDifference : getHighCardRankDifference(this.cards, otherHand.cards);
  }

  private int getHighCardRankDifference(char[] cards, char[] otherCards) {
    int rankDifference = - CardType.getRank(cards[0]) + CardType.getRank(otherCards[0]);
    return rankDifference != 0 ? rankDifference : getHighCardRankDifference(removeFirst(cards), removeFirst(otherCards));
  }

  private int getHandTypeRank() {
    return handType.rank;
  }

  private char[] removeFirst(char[] array) {
    return new String(array).substring(1).toCharArray();
  }
}
