package com.random.problems.adventOfCode.twentyThree.day11;

import com.random.util.InputUtil;
import java.util.ArrayList;
import java.util.List;

public class Expander {
  public static List<List<Character>> expand(char[][] map) {
    return expandNTimes(map, 1);
  }

  public static List<List<Character>> expandMillionTimes(char[][] map) {
    return expandNTimes(map, 1000000);
  }

  public static List<List<Character>> expandNTimes(char[][] map, int times) {
    List<List<Character>> expandedMap = InputUtil.toMatrixList(map);
    for(int i = 0; i < expandedMap.size(); i++) {
      List<Character> row = expandedMap.get(i);
      if(isClear(i, row)) {
        for(int j = 0; j < times; j++) {
          expandRow(expandedMap.get(i), i+j, expandedMap);
        }
        i++;
      }
    }
    for(int i = 0; i < expandedMap.get(0).size(); i++) {
      List<Character> column = InputUtil.getColumn(expandedMap, i);
      if(isClear(i, column)) {
        for(int j = 0; j < times; j++) {
          expandColumn(i+j, expandedMap);
        }
        i++;
      }
    }
    return expandedMap;
  }

  public static List<Integer> getExpandingRows(List<List<Character>> map) {
    List<Integer> expandingRows = new ArrayList<>();
    for(int i = 0; i < map.size(); i++) {
      List<Character> row = map.get(i);
      if(isClear(i, row)) {
        expandingRows.add(i);
      }
    }
    return expandingRows;
  }

  public static List<Integer> getExpandingColumns(List<List<Character>> map) {
    List<Integer> expandingColumns = new ArrayList<>();
    for(int i = 0; i < map.get(0).size(); i++) {
      List<Character> column = InputUtil.getColumn(map, i);
      if(isClear(i, column)) {
        expandingColumns.add(i);
      }
    }
    return expandingColumns;
  }

  private static boolean isClear(int i, List<Character> list) {
    return list.stream().allMatch(character -> character=='.');
  }

  private static void expandRow(List<Character> row, int index, List<List<Character>> map) {
    map.add(index+1, new ArrayList<>(row));
  }

  private static void expandColumn(int index, List<List<Character>> map) {
    map.forEach(row -> row.add(index+1, '.'));
  }
}
