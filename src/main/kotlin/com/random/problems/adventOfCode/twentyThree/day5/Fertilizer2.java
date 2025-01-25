package com.random.problems.adventOfCode.twentyThree.day5;

import com.random.problems.adventOfCode.AOCProblem;
import com.random.util.NumberUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Fertilizer2 extends AOCProblem {

  @Override
  public String runMethod(String input) {
    String[] sections = input.split(":");
    String[] numbers = sections[1].split("\n")[0].split(" ");


    Long minLocation = Long.MAX_VALUE;
    Long currentStart = 0L;
    List<List<Mapping>> maps = getMaps(sections);
    for(int i = 0; i < numbers.length; i++) {
      if(numbers[i].isEmpty()) {
        continue;
      }

      if(i % 2 == 1) {
        currentStart = Long.parseLong(numbers[i]);
      } else {
        Long range = Long.parseLong(numbers[i]);
        for(Long j = currentStart; j < currentStart + range; j++) {
          Long location = getLocation(j, maps);
          if(location < minLocation) {
            minLocation = location;
          }

        }
      }
    }

    return "" + minLocation;
  }


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


  private static final String ROOT = "adventOfCode/2023/day5";

  @Override
  protected String getFolderName() {
    return ROOT;
  }
}
