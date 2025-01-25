package com.random.problems.hackerrank.migratoryBirds;

import com.random.problems.AbstractProblem;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MigratoryBirds extends AbstractProblem {
    private static final String ROOT = "hackerrank/migratoryBirds";

    @Override
    public String runMethod(List<String> lines) {
        List<Integer> numbers = Arrays.stream(lines.get(1).split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        return migratoryBirds(numbers) + "";
    }

    public static int migratoryBirds(List<Integer> arr) {
        int maxId = -1;
        int max = 0;
        List<Integer> nums = arr.stream().distinct().collect(Collectors.toUnmodifiableList());
        for (Integer current : nums) {
            int sum = 0;
            for (Integer num : arr) {
                if (num.equals(current)) {
                    sum++;
                }
            }
            if (sum > max || (sum == max && current < maxId)) {
                maxId = current;
                max = sum;
            }
        }
        return maxId;
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
