package com.random.problems.adventOfCode.twentyThree.day9;

import com.random.problems.adventOfCode.AOCProblem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MirageMaintenance1 extends MirageMaintenance {
  /**
   * 1. With loops
   * */
  @Override
  protected Long getExtrapolation(List<List<Long>> pyramid) {
    return pyramid.stream().map(row -> row.get(0)).reduce(0L, Long::sum);
  }

  /**
   * 2. Recursively
   * */
  @Override
  protected int getStartColumnIndex(int index, List<Long> numbers) {
    return 0;
  }

  @Override
  protected int getPrefix(int index) {
    return 1;
  }

}
