package com.random.problems.adventOfCode.twentyThree.day5;

import com.random.problems.adventOfCode.AOCProblem;
import com.random.util.NumberUtil;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class Fertilizer extends AOCProblem {
  private static final String ROOT = "adventOfCode/2023/day5";

  @Override
  protected String getFolderName() {
    return ROOT;
  }

  @Override
  public String runMethod(String input) {
    String[] sections = input.split(":");
    List<Long> seeds = getSeeds(sections);
    List<List<Mapping>> maps = getMaps(sections);
    Long minLocation = getMinLocation(seeds, maps);
    return "" + minLocation;
  }

  abstract List<Long> getSeeds(String[] sections);

  private List<List<Mapping>> getMaps(String[] sections) {
    return Arrays.stream(sections)
        .filter(section -> section.startsWith("\n"))
        .map(this::getMap)
        .collect(Collectors.toList());
  }

  private List<Mapping> getMap(String section) {
    return Arrays.stream(section.split("\n"))
        .filter(Predicate.not(String::isEmpty))
        .filter(row -> NumberUtil.isInt(row.toCharArray()[0]))
        .map(this::getMapping)
        .collect(Collectors.toList());
  }

  private Mapping getMapping(String row) {
    String[] numbers = row.split(" ");
    return new Mapping(numbers[0], numbers[1], numbers[2]);
  }

  private Long getMinLocation(List<Long> seeds, List<List<Mapping>> maps) {
    return seeds.stream().map(seed -> getLocation(seed, maps)).reduce(Long.MAX_VALUE, Long::min);
  }

  private Long getLocation(Long seed, List<List<Mapping>> maps) {
    Long currentValue = seed;
    for(int i = 0; i < maps.size(); i++) {
      currentValue = getNextValue(currentValue, maps.get(i));
    }
    return currentValue;
  }

  private Long getNextValue(Long value, List<Mapping> mappings) {
    Mapping relevantMapping = mappings.stream().filter(mapping -> mapping.isInRange(value))
        .findFirst().orElse(new Mapping("0", "0", "1"));
    return relevantMapping.mapValue(value);
  }
}
