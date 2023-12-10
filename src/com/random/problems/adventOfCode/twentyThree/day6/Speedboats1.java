package com.random.problems.adventOfCode.twentyThree.day6;

import com.random.util.NumberUtil;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Speedboats1 extends Speedboats {
  @Override
  List<Race> getRaces(String input) {
    String[] rows = input.split("\n");
    List<List<Long>> dataRows = Arrays.stream(rows)
        .map(this::convertRowToNumbers)
        .collect(Collectors.toList());

    return IntStream.range(0, dataRows.get(0).size())
        .mapToObj(num -> new Race(dataRows.get(0).get(num), dataRows.get(1).get(num)))
        .collect(Collectors.toList());
  }

  private List<Long> convertRowToNumbers(String row) {
    return Arrays.stream(row.split(" ")).filter(NumberUtil::isLong).map(Long::parseLong).collect(Collectors.toList());
  }

}
