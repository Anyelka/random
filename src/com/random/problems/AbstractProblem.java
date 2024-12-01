package com.random.problems;

import com.random.util.FileHandler;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class AbstractProblem {
    private final static String ROOT = "resources";
    private final static String TEST_CASE = "TestCase";

    public void check(Integer... testCases) {
        System.out.println("\nChecking solution for " + this.getFolderName() + "...");
        Map<Integer, List<String>> inputDataForTestCases = readInputData(testCases);
        boolean allCorrect = inputDataForTestCases.entrySet().stream().allMatch(this::checkTestCase);
        if(allCorrect) {
            if(Arrays.asList(testCases).isEmpty()) {
                System.out.println("<<<-- CONGRATULATIONS -->>> All test results are correct");
            } else {
                System.out.println("The given test cases are correct! Lets try all test cases..");
            }

        }
    }
    private boolean checkTestCase(Map.Entry<Integer, List<String>> testCase) {
        System.out.print("      TestCase" + testCase.getKey() + " - ");
        return checkTestCase(testCase.getValue());
    }

    private boolean checkTestCase(List<String> lines) {
        String result = runMethod(lines);
        String expectedResult = getExpectedResult(lines);
        boolean isCorrect;
        if(result.equals(expectedResult)) {
            System.out.print("CORRECT solution: " + result);
            isCorrect = true;
        } else {
            System.out.print("wrong solution: " + result + ", instead of: " + expectedResult);
            isCorrect = false;
        }
        System.out.println();
        return isCorrect;
    }

    /** Run the testable code:
     *      input: list of String lines of input file
     *      return value: String line of output result
     * */
    public abstract String runMethod(List<String> lines);

    /**
     * Expected result
     * */
    public abstract String getExpectedResult(List<String> lines);
    /**
     * Folder name that contains test cases under resources folder
     * */
    protected abstract String getFolderName();

    protected Map<Integer, List<String>> readInputData(Integer... testCases) {
        if(Arrays.asList(testCases).size() == 0) {
            return FileHandler.readAllLinesInFolder(getFolderPath());
        } else {
            return FileHandler.readAllLinesFromFiles(getFilePaths(testCases));
        }
    }
    private String[] getFilePaths(Integer[] testCases) {
        return Arrays.stream(testCases).map(this::getInputPath).toArray(String[]::new);
    }
    protected String getInputPath(Integer testCaseNumber) {
        return getFolderPath() + "/" + TEST_CASE + testCaseNumber;
    }
    private String getFolderPath() {
        return ROOT + "/" + getFolderName();
    }
}
