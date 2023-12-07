package com.random.problems.adventOfCode.twentyThree.day6;

import com.random.problems.adventOfCode.AOCProblem;
import com.random.util.NumberUtil;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Speedboats2 extends Speedboats {
  @Override
  List<Race> getRaces(String input) {
    String[] rows = input.split("\n");
    List<Long> dataRows = Arrays.stream(rows)
        .map(this::convertRowToSingleNumber)
        .collect(Collectors.toList());

    return Collections.singletonList(new Race(dataRows.get(0), dataRows.get(1)));
  }

  private Long convertRowToSingleNumber(String row) {
    String number = Arrays.stream(row.split(" "))
        .filter(NumberUtil::isInt)
        .collect(Collectors.joining(""));
    return Long.parseLong(number);
  }
}
