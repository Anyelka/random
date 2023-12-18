package com.random.problems.adventOfCode.twentyThree.day11;

public class CosmicExpansion1 extends CosmicExpansion {
  private static final long EXPANSION_MULTIPLIER = 2;

  @Override
  long getExpandedCoordinateCount() {
    return EXPANSION_MULTIPLIER -1;
  }
}
