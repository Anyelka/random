package com.random.problems.adventOfCode.twentyThree.day7;

public enum CardType {
  A(14),K(13),Q(12),J(11),T(10);

  int rank;
  CardType(int i) {
    this.rank = i;
  }

  static int getRank(char card) {
    try {
      CardType cardType = CardType.valueOf("" + card);
      return cardType.rank;
    } catch(IllegalArgumentException e) {
      return Integer.parseInt("" + card);
    }
  }
}
