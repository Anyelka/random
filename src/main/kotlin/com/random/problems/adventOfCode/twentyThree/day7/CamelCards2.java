package com.random.problems.adventOfCode.twentyThree.day7;

import java.util.Comparator;

public class CamelCards2 extends CamelCards {
  @Override
  Comparator<Hand> sortHandComparator() {
    return Hand::sortByRank2;
  }

  @Override
  Hand getHand(String[] sections) {
    return Hand.of2(sections[0], sections[1]);
  }
}
