package com.random.problems.adventOfCode;

import com.random.problems.AbstractProblem;
import com.random.util.FileHandler;

import java.util.Arrays;
import java.util.List;

public abstract class AOCProblem extends AbstractProblem {
    public abstract String runMethod(String input);

    public void run() {
        String inputLines = readFile();
        System.out.println("Result for " + getFolderName() + ": \n\t\t" + runMethod(inputLines));
    }

    public void run(Integer... testCases) {
        Arrays.stream(testCases).forEach(testCase -> {
                String inputLines = readFile(testCase);
                System.out.println("Result for " + getFolderName() + ": \n\t\t" + runMethod(inputLines));
            }
        );
    }

    private String readFile() {
        return FileHandler.readFile(getInputPath(1));
    }

    private String readFile(Integer testCase) {
        return FileHandler.readFile(getInputPath(testCase));
    }

    @Override
    public String getExpectedResult(List<String> lines) {
        return "";
    }

    @Override
    public String runMethod(List<String> lines) {
        return null;
    }

}
