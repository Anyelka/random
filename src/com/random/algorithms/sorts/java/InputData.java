package com.random.algorithms.sorts.java;

import java.util.Map;

public class InputData {

    public static Map<int[], int[]> getAll() {
        return com.random.algorithms.sorts.testdata.TestDataProvider.INSTANCE.getAll();
    }

    public static Map<int[], int[]> get100kLength() {
        return com.random.algorithms.sorts.testdata.TestDataProvider.INSTANCE.get100kLength();
    }
}
