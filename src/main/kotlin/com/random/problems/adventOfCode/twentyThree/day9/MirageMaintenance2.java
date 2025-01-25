package com.random.problems.adventOfCode.twentyThree.day9;

import com.random.problems.adventOfCode.AOCProblem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MirageMaintenance2 extends MirageMaintenance {
  /**
   * 1. With loops
   * */
  @Override
  protected Long getExtrapolation(List<List<Long>> pyramid) {
    List<Long> lastItems = getLastItems(pyramid);
    List<Long> extrapolatedItems = getBackwardExtrapolatedItems(lastItems);
    return extrapolatedItems.get(extrapolatedItems.size()-1);
  }

  private List<Long> getLastItems(List<List<Long>> pyramid) {
    List<Long> lastItems = pyramid.stream().map(row -> row.get(row.size() - 1)).collect(Collectors.toList());
    Collections.reverse(lastItems);
    return lastItems;
  }

  private List<Long> getBackwardExtrapolatedItems(List<Long> lastItems) {
    long previousExtrapolation = 0;
    List<Long> backwardExtrapolatedItems = new ArrayList<>();
    for(int i = 0; i < lastItems.size(); i++) {
      Long nextItem = lastItems.get(i);
      long currentExtrapolation = nextItem - previousExtrapolation;
      backwardExtrapolatedItems.add(currentExtrapolation);
      previousExtrapolation = currentExtrapolation;
    }
    return backwardExtrapolatedItems;
  }

  /**
   * 2. Recursively
   * */

  @Override
  protected int getStartColumnIndex(int index, List<Long> numbers) {
    return numbers.size()-1-index;
  }

  @Override
  protected int getPrefix(int index) {
    return index % 2 == 0 ? (1) : (-1);
  }

}
