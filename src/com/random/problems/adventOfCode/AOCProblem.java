package com.random.problems.adventOfCode;

import com.random.problems.AbstractProblem;
import com.random.util.FileHandler;

import java.util.List;

public abstract class AOCProblem extends AbstractProblem {
    private static final String TEST_FILE_ROOT = "adventOfCode/day3";

    public abstract String runMethod(String input);

    public void run() {
        String inputLines = readFile();
        System.out.println("Result for " + getFolderName() + ": \n\t\t" + runMethod(inputLines));
    }

    private String readFile() {
        return FileHandler.readFile(getInputPath(1));
    }

    @Override
    public String getExpectedResult(List<String> lines) {
        return "";
    }

    @Override
    public String runMethod(List<String> lines) {
        return null;
    }

    @Override
    protected String getFolderName() {
        String className = this.getClass().getSimpleName();
        return TEST_FILE_ROOT + className.substring(className.length());
    }
}
