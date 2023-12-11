package com.random.problems.adventOfCode.twentyThree.day8;

import java.util.Arrays;

public class HauntedWasteland2 extends HauntedWasteland {
  @Override
  public String runMethod(String input) {
    String[] rows = input.split("\n");
    char[] leftRightInstructions = rows[0].toCharArray();
    String[] stepRows = getStepRows(rows);
    String[] currentSteps = findAllStartingSteps(stepRows);
    int stepCount = 0;
    int instructionIndex = 0;
    while(true) {
      if(finished(currentSteps)) {
        return "" + stepCount;
      }

      char nextInstruction = leftRightInstructions[instructionIndex];
      instructionIndex++;
      currentSteps = getNexSteps(currentSteps, nextInstruction, stepRows);
      stepCount++;

      if(instructionIndex == leftRightInstructions.length) {
        instructionIndex = 0;
      }
    }
  }

  private String[] getNexSteps(String[] currentStepRows, char nextInstruction, String[] stepRows) {
    return Arrays.stream(currentStepRows).map(step -> getNexStep(step, nextInstruction, stepRows)).toArray(String[]::new);
  }

  private boolean finished(String[] currentSteps) {
    return Arrays.stream(currentSteps).allMatch(this::finished);
  }

  private boolean finished(String steps) {
    return steps.endsWith("Z");
  }

  private String[] findAllStartingSteps(String[] stepRows) {
    return Arrays.stream(stepRows).filter(this::starting).map(super::getName).toArray(String[]::new);
  }

  private boolean starting(String stepRow) {
    return getName(stepRow).endsWith("A");
  }
}
