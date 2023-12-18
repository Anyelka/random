package com.random.problems.adventOfCode.twentyThree.day11;

import com.random.problems.adventOfCode.AOCProblem;
import com.random.util.InputUtil;
import java.util.ArrayList;
import java.util.List;

public abstract class CosmicExpansion extends AOCProblem {
  private static final String ROOT = "adventOfCode/2023/day11";

  @Override
  protected String getFolderName() {
    return ROOT;
  }

  @Override
  public String runMethod(String input) {
    List<List<Character>> map = InputUtil.toMatrixList(InputUtil.toCharMatrix(input));
    List<Galaxy> galaxies = getGalaxies(map);
    List<Integer> expandingRows = Expander.getExpandingRows(map);
    List<Integer> expandingColumns = Expander.getExpandingColumns(map);
    return "" + getTotalDistances(galaxies, expandingRows, expandingColumns);
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

  private long getTotalDistances(List<Galaxy> galaxies,
                                 List<Integer> expandingRows,
                                 List<Integer> expandingColumns) {
    return galaxies.stream()
        .map(galaxy -> getDistance(galaxy, galaxies, expandingRows, expandingColumns))
        .reduce(0L, Long::sum) / 2;
  }

  private long getDistance(Galaxy galaxy, List<Galaxy> galaxies,
                           List<Integer> expandingRows,
                           List<Integer> expandingColumns) {
    return galaxies.stream()
        .map(otherGalaxy -> getDistance(galaxy, otherGalaxy, expandingRows, expandingColumns))
        .reduce(0L, Long::sum);
  }

  private long getDistance(Galaxy galaxy, Galaxy otherGalaxy,
                           List<Integer> expandingRows,
                           List<Integer> expandingColumns) {
    if(galaxy.equals(otherGalaxy)) {
      return 0;
    }
    return getDistance(otherGalaxy.row, galaxy.row, expandingRows)
        + getDistance(otherGalaxy.column, galaxy.column, expandingColumns);
  }

  private int getDistance(int coordinate1, int coordinate2, List<Integer> expandingCoordinates) {
    int baseDistance = Math.abs(coordinate1 - coordinate2);
    for(int i = 0; i < expandingCoordinates.size(); i++) {
      if(isBetween(expandingCoordinates.get(i), coordinate1, coordinate2)) {
        baseDistance += getExpandedCoordinateCount();
      }
    }
    return baseDistance;
  }

  private boolean isBetween(Integer number, int edge1, int edge2) {
    return (edge1 > number && number > edge2) || (edge1 < number && number < edge2);
  }

  abstract long getExpandedCoordinateCount();
}
