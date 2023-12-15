package com.random.problems.adventOfCode.twentyThree.day10;

import com.random.problems.adventOfCode.AOCProblem;

public abstract class PipeMaze extends AOCProblem {
  private static final String ROOT = "adventOfCode/2023/day10";

  @Override
  protected String getFolderName() {
    return ROOT;
  }

  @Override
  public String runMethod(String input) {
    return findFurthestPointInPipe(input);
  }

  protected abstract String findFurthestPointInPipe(String input);
}
