package com.random.problems.hackerrank.nonDivisibleSubset;

import com.random.problems.AbstractProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


public class NonDivisibleSubset extends AbstractProblem {
    private static final String ROOT = "hackerrank/nonDivisibleSubset";

    @Override
    protected String getFolderName() {
        return ROOT;
    }

    @Override
    public String runMethod(List<String> lines) {
        int divisor = Integer.parseInt(lines.get(0).split(" ")[1]);
        List <Integer> numbers = Arrays.stream(lines.get(1).split(" ")).map(Integer::parseInt).collect(Collectors.toUnmodifiableList());
        return nonDivisibleSubset(divisor, numbers) + "";
    }

    @Override
    public String getExpectedResult(List<String> lines) {
        return Integer.parseInt(lines.get(3)) + "";
    }

    public static int nonDivisibleSubset(int k, List<Integer> s) {
        // call respective method of solution
        return nonDivisibleSubsetWithRemainders(k, s);
    }

    // 1. remainders
    //  calculating the frequency of remainders
    public static int nonDivisibleSubsetWithRemainders(int divisor, List<Integer> nums) {
        if (divisor == 1) {
            return 1;
        }
        int result = 0;
        List<Integer> numbers = nums.stream().map(num -> num % divisor).collect(Collectors.toList());
        Map<Integer, Long> occurences = numbers.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        for (Map.Entry<Integer, Long> current : occurences.entrySet()) {
            if (current.getKey() == 0) {
                result += 1;
                continue;
            }
            Map.Entry<Integer, Long> matching = null;
            for (Map.Entry<Integer, Long> other : occurences.entrySet()) {
                if ((current.getKey() + other.getKey()) == divisor) {
                    matching = other;
                    break;
                }

            }

            if(matching == null) {
                result += current.getValue();
            } else {
                if(current.getValue().equals(matching.getValue())) {
                    result += 1;
                } else if(current.getValue() > matching.getValue()) {
                    result += current.getValue();
                }
            }
        }
        return result;
    }


    // 2. recursive solution
    //  works on smaller subsets, very slow
    public static int nonDivisibleSubsetRecursively(int k, List<Integer> s) {
        return nonDivisibleSubsetWithRemainders(k, s, 0);
    }

    public static int nonDivisibleSubsetWithRemainders(int divisor, List<Integer> numbers, int start) {
        System.out.println("Numbers: " + numbers);
        if (divisor == 1) {
            return 1;
        }
        if (start >= numbers.size()) {
            return numbers.size();
        }

        Integer current = numbers.get(start);
        System.out.println("--- " + current + ":");

        for (Integer element : numbers) {
            if (!current.equals(element) && (current + element) % divisor == 0) {
                List<Integer> newNumberList1 = new ArrayList<>(numbers);
                newNumberList1.remove(current);
                int max1 = nonDivisibleSubsetWithRemainders(divisor, newNumberList1, start);

                List<Integer> newNumberList2 = new ArrayList<>(numbers);
                newNumberList2.remove(element);
                int max2 = nonDivisibleSubsetWithRemainders(divisor, newNumberList2, start + 1);

                return Math.max(max1, max2);
            }
        }

        return nonDivisibleSubsetWithRemainders(divisor, numbers, start + 1);
    }

    // 3. with loop
    //  calculating subset sizes for each number
    // works, just too slow
    public static int nonDivisibleSubsetWithLoop(int k, List<Integer> s) {
        if (k == 1) {
            return 1;
        }
        int biggestSubset = 0;
        for (int i = 0; i < s.size(); i++) {
            Integer current = s.get(i);
            List<Integer> currentSubset = new ArrayList<>();
            if (current % k != 0) {
                currentSubset.add(current);

                for (int j = 0; j < s.size(); j++) {
                    if (j != i) {
                        Integer other = s.get(j);
                        if ((other + current) % k != 0) {
                            boolean canAddToSubset = currentSubset.stream()
                                    .filter(e -> !e.equals(other))
                                    .noneMatch(e -> (e + other) % k == 0);
                            if (canAddToSubset) {
                                currentSubset.add(other);
                            }
                        }
                    }
                }
            }
            if (currentSubset.size() > biggestSubset) {
                biggestSubset = currentSubset.size();
            }
        }
        return biggestSubset;
    }
}
