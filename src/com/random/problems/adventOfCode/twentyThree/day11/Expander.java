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
          expandedMap = expandRow(expandedMap.get(i), i+j, expandedMap);
        }
        i++;
      }
    }
    for(int i = 0; i < expandedMap.get(0).size(); i++) {
      List<Character> column = InputUtil.getColumn(expandedMap, i);
      if(isClear(i, column)) {
        for(int j = 0; j < times; j++) {
          expandedMap = expandColumn(i+j, expandedMap);
        }
        i++;
      }
    }
    return expandedMap;
  }

  private static boolean isClear(int i, List<Character> list) {
    return list.stream().allMatch(character -> character=='.');
  }

  private static List<List<Character>> expandRow(List<Character> row, int index, List<List<Character>> map) {
    map.add(index+1, new ArrayList<>(row));
    return map;
  }

  private static List<List<Character>> expandColumn(int index, List<List<Character>> map) {
    map.forEach(row -> row.add(index+1, '.'));
    return map;
  }
}
