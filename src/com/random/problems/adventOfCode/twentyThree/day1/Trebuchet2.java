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
    return getValueInOneStep(rows);
  }

  /**
   * 1.Solution: converting each row to contain only numbers with digits &
   *              running the original algorithm on them one-by-one
   * */
  private String getValueWithConversion(String[] rows) {
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

  /**
   * 2.Solution: getting the result for each row in one step
   * */
  private String getValueInOneStep(String[] rows) {
    int result = Arrays.stream(rows)
        .map(this::getValue)
        .map(Integer::parseInt)
        .reduce(0, Integer::sum);
    return "" + result;
  }

  private String getValue(String line) {
    char[] chars = line.toCharArray();
    int firstNum = 0;
    int lastNum = 0;
    for(int i = 0; i < chars.length; i++) {
      char first = chars[i];
      char last = chars[chars.length-i-1];
      if(firstNum == 0) {
        if(isInt(first)) {
          firstNum = Integer.parseInt(first + "");
        } else {
          for(int j = 0; j < stringNumberList.size(); j++) {
            String numberAsString = stringNumberList.get(j);
            int numberLength = numberAsString.length();
            if(i + numberLength <= line.length() && line.substring(i, i + numberLength).equals(numberAsString)) {
              firstNum = j + 1;
              break;
            }
          }
        }
      }
      if(lastNum == 0) {
        if(isInt(last)) {
          lastNum = Integer.parseInt(last + "");
        } else {
          for(int j = 0; j < stringNumberList.size(); j++) {
            String numberAsString = stringNumberList.get(j);
            int numberLength = numberAsString.length();
            if(chars.length - i - numberLength >= 0 && line.substring(chars.length - i - numberLength, chars.length - i).equals(numberAsString)) {
              lastNum = j + 1;
              break;
            }
          }
        }
      }
      if(firstNum != 0 && lastNum != 0) {
        break;
      }
    }

    String result = (firstNum > 0 ? firstNum : "") + "" + (lastNum > 0 ? lastNum : "");
    return result.equals("") ? "0" : result;
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
