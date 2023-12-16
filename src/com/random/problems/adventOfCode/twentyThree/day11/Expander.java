package com.random.problems.adventOfCode.twentyThree.day11;

import com.random.util.InputUtil;
import java.util.ArrayList;
import java.util.List;

public class Expander {
  public static List<List<Character>> expand(char[][] map) {
    List<List<Character>> expandedMap = InputUtil.toMatrixList(map);
    for(int i = 0; i < expandedMap.size(); i++) {
      List<Character> row = expandedMap.get(i);
      if(isClear(i, row)) {
        expandedMap = expandRow(i, expandedMap);
        i++;
      }
    }
    for(int i = 0; i < expandedMap.get(0).size(); i++) {
      List<Character> column = InputUtil.getColumn(expandedMap, i);

      if(isClear(i, column)) {
        expandedMap = expandColumn(i, expandedMap);
        i++;
      }
    }
    return expandedMap;
  }

  private static boolean isClear(int i, List<Character> list) {
    return list.stream().allMatch(character -> character=='.');
  }

  private static List<List<Character>> expandRow(int i, List<List<Character>> map) {
    map.add(i+1, new ArrayList<>(map.get(i)));
    return map;
  }

  private static List<List<Character>> expandColumn(int i, List<List<Character>> map) {
    map.forEach(row -> row.add(i+1, '.'));
    return map;
  }
}
