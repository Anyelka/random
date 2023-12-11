package com.random.problems.adventOfCode.twentyThree.day8;

import com.random.problems.adventOfCode.AOCProblem;
import java.util.Arrays;

public abstract class HauntedWasteland extends AOCProblem {
  private static final String ROOT = "adventOfCode/2023/day8";

  @Override
  protected String getFolderName() {
    return ROOT;
  }

  protected String[] getStepRows(String[] rows) {
    return Arrays.stream(rows).filter(row -> row.contains("=")).toArray(String[]::new);
  }

  protected String getNexStep(String step, char instruction, String[] stepRows) {
    String stepRow = Arrays.stream(stepRows).filter(row -> getName(row).equals(step)).findFirst().get();
    String[] nextStepNames = getNextStepNames(stepRow);
    return getNextStep(nextStepNames, instruction);
  }

  protected String getNextStep(String[] nextStepNames, char instruction) {
    if(instruction=='L'){
      return nextStepNames[0];
    }
    if(instruction=='R'){
      return nextStepNames[1];
    }
    return null;
  }


  protected String getName(String row) {
    return row.split("=")[0].strip();
  }

  protected String[] getNextStepNames(String row) {
    return row.split("=")[1].replaceAll("[()]", "").replaceAll(" ", "").split(",");
  }

}
