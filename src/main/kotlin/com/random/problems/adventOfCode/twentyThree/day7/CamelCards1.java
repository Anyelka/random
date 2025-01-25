package com.random.problems.adventOfCode.twentyThree.day7;

import java.util.Comparator;

public class CamelCards1 extends CamelCards {
  @Override
  Comparator<Hand> sortHandComparator() {
    return Hand::sortByRank1;
  }

  @Override
  Hand getHand(String[] sections) {
    return Hand.of1(sections[0], sections[1]);
  }
}
