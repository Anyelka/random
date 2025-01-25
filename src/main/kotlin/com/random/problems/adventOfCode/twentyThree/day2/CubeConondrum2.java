package com.random.problems.adventOfCode.twentyThree.day2;

import com.random.problems.adventOfCode.AOCProblem;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CubeConondrum2 extends AOCProblem {
  private static final String ROOT = "adventOfCode/2023/day2/part2";

  @Override
  protected String getFolderName() {
    return ROOT;
  }

  @Override
  public String runMethod(String input) {
    String[] rows = input.split("\n");

    Integer sum = Arrays.stream(rows).map(this::getRowValue).reduce(0, Integer::sum);
    return sum + "";
  }

  private int getRowValue(String row) {
    if(row.startsWith("Game")) {
      String[] sections = row.split(":");
      String[] picks = sections[1].replaceAll(";", ",").split(", ");

      Map<Color, Integer> minPicks = new HashMap<>();

      for(String pick: picks) {
        String[] pickParts = pick.stripLeading().stripTrailing().split(" ");
        int pickCount = Integer.parseInt(pickParts[0]);
        Color pickColor = Color.of(pickParts[1]);
        Integer prevMinPick = minPicks.get(pickColor);
        if(prevMinPick == null || prevMinPick < pickCount) {
          minPicks.put(pickColor, pickCount);
        }
      }
      return minPicks.values().stream().reduce(1, (subTotal, value) -> subTotal * value);
    }
    return 0;
  }

  private enum Color {
    RED("red"), BLUE("blue"), GREEN("green");

    String name;

    Color(String name) {
      this.name = name;
    }

    static Color of(String name) {
      return Arrays.stream(Color.values()).filter(color -> color.name.equals(name)).findFirst().get();
    }
  }
}
