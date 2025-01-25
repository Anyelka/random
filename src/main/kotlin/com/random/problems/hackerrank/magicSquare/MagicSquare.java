package com.random.problems.hackerrank.magicSquare;

import com.random.problems.AbstractProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MagicSquare extends AbstractProblem {
    private static final String ROOT = "hackerrank/magicSquare";

    @Override
    protected String getFolderName() {
        return ROOT;
    }

    @Override
    public String runMethod(List<String> lines) {
        List<List<Integer>> matrix = readMatrix(lines);
        return formingMagicSquare(matrix) + "";
    }

    private List<List<Integer>> readMatrix(List<String> lines) {
        return IntStream.range(0, MAGIC_SQUARE_SIZE).boxed()
                .map(i -> Arrays.stream(lines.get(i).split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toUnmodifiableList()))
                .collect(Collectors.toUnmodifiableList());
    }

    public static int formingMagicSquare(List<List<Integer>> matrix) {
        return formingMagicSquare2(matrix);
    }

    private static final int NO_OF_MAGIC_SQUARES = 8;

    private static int formingMagicSquare2(List<List<Integer>> matrix) {
        return formingMagicSquareRecursively(matrix, 0);
    }

    private static int formingMagicSquareRecursively(List<List<Integer>> matrix, int index) {
        if(index == NO_OF_MAGIC_SQUARES) {
            return Integer.MAX_VALUE;
        }
        List<List<Integer>> nextMatrix = index == (NO_OF_MAGIC_SQUARES/2 -1) ? mirror(matrix) : rotate(matrix);
        return Math.min(magicSquareCost(matrix), formingMagicSquareRecursively(nextMatrix, index + 1));
    }

    /**
     * Solution1:
     *  This was the first idea for implementation
     *      Checking the differences to all of the 8 possible magic square matrices and
     *      returning the lowest one.
     */
    private static final int MAGIC_SQUARE_SIZE = 3;
    private static final List<List<Integer>> MAGIC_SUQARE = List.of(
            List.of(8, 3, 4),
            List.of(1, 5, 9),
            List.of(6, 7, 2)
    );

    private static int formingMagicSquare1(List<List<Integer>> matrix) {
        List<List<Integer>> m1 = matrix;
        List<List<Integer>> m2 = rotate(m1);
        List<List<Integer>> m3 = rotate(m2);
        List<List<Integer>> m4 = rotate(m3);
        int cost1 = magicSquareCost(m1);
        int cost2 = magicSquareCost(m2);
        int cost3 = magicSquareCost(m3);
        int cost4 = magicSquareCost(m4);

        List<List<Integer>> m5 = mirror(m1);
        List<List<Integer>> m6 = mirror(m2);
        List<List<Integer>> m7 = mirror(m3);
        List<List<Integer>> m8 = mirror(m4);
        int cost5 = magicSquareCost(m5);
        int cost6 = magicSquareCost(m6);
        int cost7 = magicSquareCost(m7);
        int cost8 = magicSquareCost(m8);
        return Math.min(
                Math.min(
                        Math.min(cost3, cost4),
                        Math.min(cost1, cost2)
                ),
                Math.min(
                        Math.min(cost5, cost6),
                        Math.min(cost7, cost8)
                )
        );
    }

    private static List<List<Integer>> rotate(List<List<Integer>> matrix) {
        List<List<Integer>> result = new ArrayList<>();
        int size = matrix.size();
        for(int i = 0; i < size; i++) {
            List<Integer> row = new ArrayList<>();
            for(int j = 0; j < size; j++) {
                row.add(matrix.get(j).get(size-1-i));
            }
            result.add(row);
        }
        return result;
    }

    private static List<List<Integer>> mirror(List<List<Integer>> matrix) {
        List<List<Integer>> result = new ArrayList<>();
        int size = matrix.size();
        for(int i = 0; i < size; i++) {
            List<Integer> row = new ArrayList<>();
            for(int j = 0; j < size; j++) {
                row.add(matrix.get(j).get(i));
            }
            result.add(row);
        }
        return result;
    }

    private static int magicSquareCost(List<List<Integer>> matrix) {
        return transformCost(matrix, MAGIC_SUQARE);
    }

    private static int transformCost(List<List<Integer>> matrix, List<List<Integer>> result) {
        int cost = 0;
        int size = matrix.size();
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                cost += Math.abs(matrix.get(i).get(j)-result.get(i).get(j));
            }
        }
        return cost;
    }


    @Override
    public String getExpectedResult(List<String> lines) {
        return lines.get(4);
    }
}
