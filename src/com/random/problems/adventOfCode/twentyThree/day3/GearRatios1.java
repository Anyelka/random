package com.random.problems.adventOfCode.twentyThree.day3;

import com.random.problems.adventOfCode.AOCProblem;
import com.random.util.NumberUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GearRatios1 extends AOCProblem {
  private static final String ROOT = "adventOfCode/2023/day3";

  @Override
  protected String getFolderName() {
    return ROOT;
  }

  @Override
  public String runMethod(String input) {
    char[][] inputMatrix = Arrays.stream(input.split("\n")).map(String::toCharArray).toArray(char[][]::new);
    Map<NumberCoordinates, Integer> numbersWithCoordinates = readNumbers(inputMatrix);
    return "" + numbersWithCoordinates.entrySet().stream()
        .filter(num -> hasAdjacentSymbol(num, inputMatrix))
        .map(Map.Entry::getValue)
        .reduce(0, Integer::sum);
  }

  private Map<NumberCoordinates, Integer> readNumbers(char[][] inputMatrix) {
    Map<NumberCoordinates, Integer> result = new HashMap<>();

    boolean readingNumber = false;
    int rowCoordinate = 0;
    Integer[] columnCoordinates = new Integer[2];
    List<Integer> currentNumbers = new ArrayList<>();
    for(int i = 0; i < inputMatrix.length; i++) {
      for(int j = 0; j < inputMatrix[i].length; j++) {
        char current = inputMatrix[i][j];
        if(NumberUtil.isInt(current)) {
          if(!readingNumber) {
            rowCoordinate = i;
            columnCoordinates[0] = j;
          }
          readingNumber = true;
          currentNumbers.add(Integer.parseInt("" + current));
          if(j == inputMatrix[i].length - 1) {
            columnCoordinates[1] = j;
            result.put(new NumberCoordinates(rowCoordinate, columnCoordinates[0], columnCoordinates[1]), getNumber(currentNumbers));
            currentNumbers = new ArrayList<>();
            readingNumber = false;
          }
        } else if(readingNumber) {
          columnCoordinates[1] = j-1;
          result.put(new NumberCoordinates(rowCoordinate, columnCoordinates[0], columnCoordinates[1]), getNumber(currentNumbers));
          currentNumbers = new ArrayList<>();
          readingNumber = false;
        }
      }
    }
    return result;
  }

  private Integer getNumber(List<Integer> numbers) {
    int result = 0;
    for(int i = numbers.size()-1 ; i >= 0; i--) {
      result += numbers.get(numbers.size()-1-i)*Math.pow(10, i);
    }
    return result;
  }

  private boolean hasAdjacentSymbol(Map.Entry<NumberCoordinates, Integer> number, char[][] inputMatrix) {
    NumberCoordinates numberCoordinates = number.getKey();
    for(int i = numberCoordinates.rowIndex - 1; i <= numberCoordinates.rowIndex + 1; i++) {
      for(int j = numberCoordinates.startColumnIndex - 1; j <= numberCoordinates.endColumnIndex + 1; j++) {
        if(i >= 0 && i < inputMatrix.length && j >=0 && j < inputMatrix[i].length) {
          if(isSymbol(inputMatrix[i][j])){
            return true;
          }
        }
      }
    }

    return false;
  }

  private boolean isSymbol(char character) {
    return character != '.' && !NumberUtil.isInt(character);
  }

  private class NumberCoordinates {
    int rowIndex;
    int startColumnIndex;
    int endColumnIndex;

    public NumberCoordinates(int rowIndex, int startColumnIndex, int endColumnIndex) {
      this.rowIndex = rowIndex;
      this.startColumnIndex = startColumnIndex;
      this.endColumnIndex = endColumnIndex;
    }
  }

}
