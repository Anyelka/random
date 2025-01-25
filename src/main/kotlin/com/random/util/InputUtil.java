package com.random.util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputUtil {
  public static char[][] toCharMatrix(String input) {
    return Arrays.stream(input.split("\n")).map(String::toCharArray).toArray(char[][]::new);
  }

  public static String toString(char[][] characters) {
    return Arrays.stream(characters).map(String::valueOf).collect(Collectors.joining("\n"));
  }

  public static String toString(List<List<Character>> matrix) {
    return matrix.stream().map(row -> row.stream().map(String::valueOf).collect(Collectors.joining("")))
        .collect(Collectors.joining("\n"));
  }

  public static List<List<Character>> toMatrixList(char[][] array) {
    return Arrays.stream(array).map(InputUtil::toList).collect(Collectors.toList());
  }

  public static List<Character> toList(char[] array) {
    return IntStream.range(0, array.length).mapToObj(i->array[i]).collect(Collectors.toList());
  }

  public static List<Character> getColumn(List<List<Character>> expandedMap, int i) {
    return expandedMap.stream().map(row -> row.get(i)).collect(Collectors.toList());
  }
}
