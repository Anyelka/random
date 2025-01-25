package com.random.problems.adventOfCode.twentyThree.day3;

import com.random.problems.adventOfCode.AOCProblem;
import com.random.util.NumberUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GearRatios2 extends AOCProblem {
  private static final String ROOT = "adventOfCode/2023/day3";

  @Override
  protected String getFolderName() {
    return ROOT;
  }

  @Override
  public String runMethod(String input) {
    char[][] inputMatrix = Arrays.stream(input.split("\n")).map(String::toCharArray).toArray(char[][]::new);
    return "" + getGearRatios(inputMatrix);
  }

  private int getGearRatios(char[][] inputMatrix) {
    int totalGearRatio = 0;
    for(int i = 0; i < inputMatrix.length; i++) {
      for(int j = 0; j < inputMatrix[i].length; j++) {
        if (inputMatrix[i][j] == '*') {
          totalGearRatio += getGearRatio(i,j, inputMatrix);
        }
      }
    }
    return totalGearRatio;
  }

  private int getGearRatio(int i, int j, char[][] inputMatrix) {
    List<Integer> numbers = getNumbers(i,j, inputMatrix);
    return numbers.size() == 2 ? numbers.get(0) * numbers.get(1) : 0;
  }

  private List<Integer> getNumbers(int i, int j, char[][] inputMatrix) {
    List<Integer> numbers = new ArrayList<>();
    for(int x = -1; x <= 1; x++) {
      for(int y = -1; y <= 1; y++) {
        if(i+x>=0 && i+x<inputMatrix.length && j+y>=0 && j+y<inputMatrix[i+x].length) {
          char currentChar = inputMatrix[i+x][j+y];

          if(NumberUtil.isInt(currentChar)) {
            char prev2Char = inputMatrix[i+x][j+y-2];
            char prev1Char = inputMatrix[i+x][j+y-1];
            char next1Char = inputMatrix[i+x][j+y+1];
            char next2Char = inputMatrix[i+x][j+y+2];
            int number = getNumber(prev2Char, prev1Char, currentChar, next1Char, next2Char);
            if(!numbers.contains(number)) {
              numbers.add(number);
            }
          }
        }
      }
    }
    return numbers;
  }

  private int getNumber(char prev2Char, char prev1Char, char currentChar, char next1Char, char next2Char) {
    if(NumberUtil.isInt(prev2Char) && NumberUtil.isInt(prev1Char)) {
      return Integer.parseInt("" +prev2Char) * 100 + Integer.parseInt("" +prev1Char) * 10 + Integer.parseInt(""+currentChar);
    }
    if(NumberUtil.isInt(next1Char) && NumberUtil.isInt(next2Char)) {
      return Integer.parseInt("" +currentChar) * 100 + Integer.parseInt("" +next1Char) * 10 + Integer.parseInt(""+next2Char);
    }
    if(NumberUtil.isInt(prev1Char) && NumberUtil.isInt(next1Char)) {
      return Integer.parseInt("" +prev1Char) * 100 + Integer.parseInt("" +currentChar) * 10 + Integer.parseInt(""+next1Char);
    }
    if(NumberUtil.isInt(prev1Char)) {
      return Integer.parseInt("" +prev1Char) * 10 + Integer.parseInt(""+currentChar);
    }
    if(NumberUtil.isInt(next1Char)) {
      return Integer.parseInt("" +currentChar) * 10 + Integer.parseInt(""+next1Char);
    }
    return Integer.parseInt(""+currentChar);
  }

  private class StarCoordinates {
    int rowIndex;
    int columnIndex;

    public StarCoordinates(int rowIndex, int columnIndex) {
      this.rowIndex = rowIndex;
      this.columnIndex = columnIndex;
    }
  }
}
