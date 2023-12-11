package com.random.problems.adventOfCode.twentyThree.day8;

public class HauntedWasteland1 extends HauntedWasteland {
  @Override
  public String runMethod(String input) {
    String[] rows = input.split("\n");
    char[] leftRightInstructions = rows[0].toCharArray();
    String[] stepRows = getStepRows(rows);
    String currentStep = "AAA";
    int stepCount = 0;
    int instructionIndex = 0;
    while(true) {
      if(currentStep.equals("ZZZ")) {
        return "" + stepCount;
      }

      char nextInstruction = leftRightInstructions[instructionIndex];
      instructionIndex++;
      currentStep = getNexStep(currentStep, nextInstruction, stepRows);
      stepCount++;

      if(instructionIndex == leftRightInstructions.length) {
        instructionIndex = 0;
      }
    }
  }

}
