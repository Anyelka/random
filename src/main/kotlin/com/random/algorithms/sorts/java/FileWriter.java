package com.random.algorithms.sorts.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Consumer;

import static com.random.algorithms.sorts.java.Formatter.formatArray;

public class FileWriter {
    public static void runWithInputOutputWrite(String inputFileName, String outputFileName, int[] array,
                                               Consumer<int[]> method) throws IOException {
        Files.writeString(Paths.get(inputFileName), formatArray(array));
        method.accept(array);
        Files.writeString(Paths.get(outputFileName), formatArray(array));
    }
}
