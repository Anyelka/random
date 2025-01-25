package com.random.problems.hackerrank.pickingNumbers;

import com.random.problems.AbstractProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PickingNumbers extends AbstractProblem {
    private static final String ROOT = "hackerrank/pickingNumbers";
    private static final int MAX_DIFF = 1;

    @Override
    public String runMethod(List<String> lines) {
        List<Integer> numbers = Arrays.stream(lines.get(1).split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toUnmodifiableList());
        return pickingNumbers(numbers) + "";
    }

    public static int pickingNumbers(List<Integer> a) {
        return pickingNumbersRecursively(a);
    }

    public static int pickingNumbersRecursively(List<Integer> a) {
        return pickingNumbersRecursively(a, 0, 0, a.get(0));
    }

    public static int pickingNumbersRecursively(List<Integer> a, int start, int maxDiff, int prevNumber) {
        if (start + 1 >= a.size()) {
            return 0;
        }
        Integer current = a.get(start);
        Integer next = a.get(start + 1);
        int currentDiff = prevNumber - current;
        int nextDiff = maxDiff == 0 ? maxDiff : currentDiff;
        int addition;
        int nextStart;
        int nextPrevNumber;
        if (Math.abs(currentDiff) <= 1 && Math.abs(currentDiff + maxDiff) <= 1) {
            addition = 1;
            nextStart = start + 1;
            nextPrevNumber = current;
        } else {
            addition = 0;
            nextStart = start + 1;
            nextPrevNumber = next;
        }
        int currentMax = addition + pickingNumbersRecursively(a, nextStart, nextDiff, nextPrevNumber);
        return Math.max(
                currentMax,
                pickingNumbersRecursively(a, start + 1, 0, next)
        );
    }

    /**
     * with iteration
     */
    private static int pickingNumbers1(List<Integer> a) {
        List<Integer> numbers = a.stream().sorted().collect(Collectors.toUnmodifiableList());
        int maxSize = 1;
        for (int i = 0; i < numbers.size(); i++) {
            List<Integer> subarray = new ArrayList<Integer>();
            subarray.add(numbers.get(i));
            for (int j = i + 1; j < numbers.size(); j++) {
                Integer current = numbers.get(j);
                if (subarray.stream().allMatch(n -> Math.abs(n - current) <= 1)) {
                    subarray.add(current);
                }
            }
            int currentSize = subarray.size();
            if (currentSize > maxSize) {
                maxSize = currentSize;
            }
        }
        return maxSize;
    }


    @Override
    public String getExpectedResult(List<String> lines) {
        return lines.get(3);
    }

    @Override
    protected String getFolderName() {
        return ROOT;
    }
}
