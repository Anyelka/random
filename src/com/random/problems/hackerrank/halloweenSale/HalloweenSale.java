package com.random.problems.hackerrank.halloweenSale;

import com.random.problems.AbstractProblem;
import java.util.List;

public class HalloweenSale extends AbstractProblem {
  private static final String ROOT = "hackerrank/halloweenSale";

  @Override
  public String runMethod(List<String> lines) {
    String[] input = lines.get(0).split(" ");
    return "" + howManyGames(Integer.parseInt(input[0]), Integer.parseInt(input[1]), Integer.parseInt(input[2]), Integer.parseInt(input[3]));
  }

  private int howManyGames(int p, int d, int m, int s) {
    if(s >= p) {
      int nextPrice = p - d;
      nextPrice = Math.max(nextPrice, m);
      return 1 + howManyGames(nextPrice, d, m, s-p);
    } else {
      return 0;
    }
  }

  @Override
  public String getExpectedResult(List<String> lines) {
    return "" + lines.get(2);
  }

  @Override
  protected String getFolderName() {
    return ROOT;
  }
}
