package com.random.problems.adventOfCode.twentyThree.day2;

import com.random.problems.adventOfCode.AOCProblem;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class CubeConondrum1 extends AOCProblem {
  private static final String ROOT = "adventOfCode/2023/day2";

  @Override
  protected String getFolderName() {
    return ROOT;
  }

  @Override
  public String runMethod(String input) {
    String[] rows = input.split("\n");
    String totalCubeCount = rows[0];
    Map<Color, Integer> totalCubeCounts = Arrays.stream(totalCubeCount.split(", "))
        .map(line -> line.split(" "))
        .collect(Collectors.toMap(line -> Color.of(line[1]), line -> Integer.parseInt(line[0])));

    Integer sum = Arrays.stream(rows).map(row -> getRowValue(row, totalCubeCounts)).reduce(0, Integer::sum);
    return sum + "";
  }

  private int getRowValue(String row,
                          Map<Color, Integer> totalCubeCounts) {
    if(row.startsWith("Game")) {
      String[] sections = row.split(":");
      String[] picks = sections[1].replaceAll(";", ",").split(", ");
      boolean isPossible = Arrays.stream(picks).allMatch(pick -> isPossible(pick, totalCubeCounts));
      return isPossible ? Integer.parseInt(sections[0].split(" ")[1]) : 0;
    }
    return 0;
  }

  private boolean isPossible(String pick, Map<Color, Integer> totalCubeCounts) {
    String[] pickParts = pick.stripLeading().stripTrailing().split(" ");
    return Integer.parseInt(pickParts[0]) <= totalCubeCounts.get(Color.of(pickParts[1]));
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
