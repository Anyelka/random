package com.random.problems.adventOfCode.twentyThree.day6;

import com.random.problems.adventOfCode.AOCProblem;
import java.util.List;

abstract class Speedboats extends AOCProblem {
  private static final String ROOT = "adventOfCode/2023/day6";

  @Override
  protected String getFolderName() {
    return ROOT;
  }

  @Override
  public String runMethod(String input) {
    List<Race> races = getRaces(input);
    return "" + getNumberOfPossibleWinningWays(races);
  }

  abstract List<Race> getRaces(String input);

  Long getNumberOfPossibleWinningWays(List<Race> races) {
    return races.stream().map(Race::countPossibleWinningWays).reduce(1L, (subTotal, element) -> subTotal * element);
  }

}
