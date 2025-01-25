package com.random.problems.adventOfCode.twentyThree.day11;

import com.random.problems.adventOfCode.AOCProblem;

public class CosmicExpansion2 extends CosmicExpansion {
  private static final long EXPANSION_MULTIPLIER = 1000000;

  @Override
  long getExpandedCoordinateCount() {
    return EXPANSION_MULTIPLIER -1;
  }

}
