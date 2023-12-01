package com.random.problems.adventOfCode.twentyThree.day1;

import com.random.problems.adventOfCode.AOCProblem;
import java.util.Arrays;
import java.util.List;

/**
 * --- Part Two ---
 * Your calculation isn't quite right. It looks like some of the digits are actually spelled out with letters: one, two, three, four, five, six, seven, eight, and nine also count as valid "digits".
 *
 * Equipped with this new information, you now need to find the real first and last digit on each line. For example:
 *
 * two1nine
 * eightwothree
 * abcone2threexyz
 * xtwone3four
 * 4nineeightseven2
 * zoneight234
 * 7pqrstsixteen
 * In this example, the calibration values are 29, 83, 13, 24, 42, 14, and 76. Adding these together produces 281.
 *
 * What is the sum of all of the calibration values?
 *
 * TODO: NOTE: 'eighthree' = 83 and 'sevenine' = 79 */
public class Trebuchet2 extends AOCProblem {
  private static final String ROOT = "adventOfCode/2023/day1/part2";

  private Trebuchet1 trebuchet1 = new Trebuchet1();

  @Override
  protected String getFolderName() {
    return ROOT;
  }

  @Override
  public String runMethod(String input) {
    String[] rows = input.split("\n");
    int result = Arrays.stream(rows)
        .map(this::convertLine)
        .map(trebuchet1::getValue)
        .map(Integer::parseInt)
        .reduce(0, Integer::sum);
    return "" + result;
  }

  private String convertLine(String line) {

    char[] chars = line.toCharArray();
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < line.length(); i++) {
      boolean found = false;
      if(!isInt(chars[i])) {

        for(int j = 0; j < stringNumberList.size(); j++) {
          String numberAsString = stringNumberList.get(j);
          int numberLength = numberAsString.length();
          if(i+numberLength <= line.length() && line.substring(i, i+numberLength).equals(numberAsString)) {
            result.append("" + (j+1));
            i+= numberLength-2;
            found = true;
            break;
          }
        }

      }
      if(!found) {
        result.append(chars[i]);
      }
    }
    return result.toString();
  }

  private List<String> stringNumberList = List.of(
      "one",
      "two",
      "three",
      "four",
      "five",
      "six",
      "seven",
      "eight",
      "nine"
  );

  private boolean isInt(char c) {
    try {
      Integer.parseInt(c + "");
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}
