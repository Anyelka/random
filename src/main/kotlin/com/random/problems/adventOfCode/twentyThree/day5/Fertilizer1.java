package com.random.problems.adventOfCode.twentyThree.day5;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Fertilizer1 extends Fertilizer {

  @Override
  List<Long> getSeeds(String[] sections) {
    return Arrays.stream(sections[1].split("\n")[0].split(" "))
        .filter(Predicate.not(String::isEmpty))
        .map(Long::parseLong).collect(Collectors.toList());
  }
}
