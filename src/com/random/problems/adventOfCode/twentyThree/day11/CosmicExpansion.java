package com.random.problems.adventOfCode.twentyThree.day11;

import com.random.problems.adventOfCode.AOCProblem;
import com.random.util.InputUtil;
import java.util.ArrayList;
import java.util.List;

public class CosmicExpansion extends AOCProblem {
  private static final String ROOT = "adventOfCode/2023/day11";

  @Override
  protected String getFolderName() {
    return ROOT;
  }

  @Override
  public String runMethod(String input) {
    char[][] map = InputUtil.toCharMatrix(input);
    List<List<Character>> expandedMap = Expander.expandMillionTimes(map);
    List<Galaxy> galaxies = getGalaxies(expandedMap);
    return "" + getTotalDistances(galaxies);
  }

  private List<Galaxy> getGalaxies(List<List<Character>> expandedMap) {
    List<Galaxy> galaxies = new ArrayList<>();
    for(int i = 0; i < expandedMap.size(); i++) {
      List<Character> row = expandedMap.get(i);
      for(int j = 0; j < row.size(); j++) {
        Character character = row.get(j);
        if(character=='#') {
          galaxies.add(new Galaxy(i,j));
        }
      }
    }
    return galaxies;
  }


  private long getTotalDistances(List<Galaxy> galaxies) {
    return galaxies.stream().map(galaxy -> getDistance(galaxy, galaxies)).reduce(0L, Long::sum) / 2;
  }

  private long getDistance(Galaxy galaxy, List<Galaxy> galaxies) {
    return galaxies.stream().map(otherGalaxy -> getDistance(galaxy, otherGalaxy)).reduce(0L, Long::sum);
  }

  private long getDistance(Galaxy galaxy, Galaxy otherGalaxy) {
    if(galaxy.equals(otherGalaxy)) {
      return 0;
    }
    return Math.abs(otherGalaxy.row-galaxy.row) + Math.abs(otherGalaxy.column-galaxy.column);
  }
}
