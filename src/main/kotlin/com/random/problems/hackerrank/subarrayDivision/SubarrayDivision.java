package com.random.problems.hackerrank.subarrayDivision;

import com.random.problems.AbstractProblem;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SubarrayDivision extends AbstractProblem {
    private static final String ROOT = "hackerrank/subarrayDivision";

    @Override
    protected String getFolderName() {
        return ROOT;
    }

    @Override
    public String runMethod(List<String> lines) {
        List<Integer> numbers = Arrays.asList(lines.get(1).split(" ")).stream()
                .map(Integer::parseInt)
                .collect(Collectors.toUnmodifiableList());
        String[] line3 = lines.get(2).split(" ");
        int days = Integer.parseInt(line3[0]);
        int months = Integer.parseInt(line3[1]);
        return "" + birthday(numbers, days, months);
    }

    @Override
    public String getExpectedResult(List<String> lines) {
        return lines.get(4).trim();
    }

    public static int birthday(List<Integer> s, int d, int m) {
        return birthdayRecursively(0, s, d, m, false);
    }

    public static int birthdayRecursively(int start, List<Integer> s, int d, int m, boolean inSequence) {
        if (d == 0 && m == 0) {
            return 1;
        } else if (d < 0 || m == 0) {
            return 0;
        }

        if (start > s.size() - m) {
            return 0;
        }

        Integer current = s.get(start);
        int addition = birthdayRecursively(start + 1, s, d - current, m - 1, true);

        if(inSequence) {
            return addition;
        }

        return addition + birthdayRecursively(start + 1, s, d, m, false);
    }
}
