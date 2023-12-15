package com.random.problems.adventOfCode.twentyThree.day9;

import com.random.problems.adventOfCode.AOCProblem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class MirageMaintenance extends AOCProblem {
  private static final String ROOT = "adventOfCode/2023/day9";

  @Override
  protected String getFolderName() {
    return ROOT;
  }

  @Override
  public String runMethod(String input) {
    String[] histories = input.split("\n");
    return "" + Arrays.stream(histories).map(this::getNextHistoryValue).reduce(0L, Long::sum);
  }

  private long getNextHistoryValue(String history) {
    List<Long> numbers = getNumbers(history);
    return getNextHistoryValueRecursively(numbers);
  }

  private List<Long> getNumbers(String history) {
    List<Long> numbers = Arrays.stream(history.split(" "))
        .map(Long::parseLong)
        .collect(Collectors.toList());
    Collections.reverse(numbers);
    return numbers;
  }

  /**
   * 1. With loop
   */
  private Long getNextHistoryWithLoop(List<Long> numbers) {
    List<List<Long>> pyramid = getPyramid(numbers);
    return getExtrapolation(pyramid);
  }

  protected abstract Long getExtrapolation(List<List<Long>> pyramid);

  private List<List<Long>> getPyramid(List<Long> numbers) {
    List<List<Long>> pyramid = new ArrayList<>();
    pyramid.add(numbers);
    for(int i = 1; i < numbers.size(); i++) {
      List<Long> row = new ArrayList<>();
      for(int j = 0; j < numbers.size() - i; j++) {
        List<Long> prevRow = pyramid.get(i - 1);
        Long current = prevRow.get(j) - prevRow.get(j + 1);
        row.add(current);
      }
      pyramid.add(row);
      if(row.stream().allMatch(number -> number == 0)) {
        break;
      }
    }
    return pyramid;
  }

  /**
   * 2. Recursively
   * */
  protected Long getNextHistoryValueRecursively(List<Long> numbers) {
    return getNextLevelValue(0, numbers);
  }

  private Long getNextLevelValue(int index, List<Long> numbers) {
    if(index == numbers.size()) {
      return 0L;
    }
    Long current = nextValue(index, numbers.size()-1-index, numbers);
    return getPrefix(index) * current + getNextLevelValue(index+1, numbers);
  }

  protected long nextValueWithStartColumn(int index, List<Long> numbers) {
    return nextValue(index, getStartColumnIndex(index, numbers), numbers);
  }

  private long nextValue(int row, int column, List<Long> numbers) {
    if(row == 0) {
      return numbers.get(column);
    }
    return nextValue(row - 1, column, numbers) - nextValue(row - 1, column + 1, numbers);
  }

  protected abstract int getStartColumnIndex(int index, List<Long> numbers);

  protected abstract int getPrefix(int index);
}
