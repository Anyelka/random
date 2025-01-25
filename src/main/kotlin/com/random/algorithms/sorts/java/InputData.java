package com.random.algorithms.sorts.java;

import com.random.algorithms.sorts.testdata.TestDataProvider;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class InputData {

    public static Map<int[], int[]> getAll() {
        return toPrimitiveIntArray(TestDataProvider.INSTANCE.getAll());
    }

    public static Map<int[], int[]> get100kLength() {
        return toPrimitiveIntArray(TestDataProvider.INSTANCE.get100kLength());
    }

    private static Map<int[], int[]> toPrimitiveIntArray(Map<Integer[], Integer[]> map) {
        return map.entrySet()
                .stream()
                .map(entry -> new AbstractMap.SimpleEntry<>(
                        Arrays.stream(entry.getKey()).mapToInt(i -> i).toArray(),
                        Arrays.stream(entry.getValue()).mapToInt(i -> i).toArray()
                ))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
