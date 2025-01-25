package com.random.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileHandler {
    public static Map<Integer, List<String>> readAllLinesInFolder(String folderPath) {
        try (Stream<Path> paths = Files.walk(Paths.get(folderPath))) {
            return readAllLinesFromPaths(paths);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file(s) from path: " + folderPath);
        }
    }

    public static Map<Integer, List<String>> readAllLinesFromFiles(String[] filePaths) {
        Stream<Path> paths = Arrays.stream(filePaths).map(Path::of);
        return readAllLinesFromPaths(paths);
    }

    private static Map<Integer, List<String>> readAllLinesFromPaths(Stream<Path> paths) {
        return paths.filter(Files::isRegularFile)
                .collect(Collectors.toMap(p -> {
                    String fileName = p.getFileName().toString();
                    String fileNumber = fileName.replaceAll("[^0-9]", "");
                    return Integer.parseInt(fileNumber);
                }, FileHandler::readAllLines));
    }

    private static List<String> readAllLines(Path path) {
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException("Error reading file from path: " + path);
        }
    }

    public static String readFile(String folderPath) {
        try {
            return Files.readString(Paths.get(folderPath));
        } catch (IOException e) {
            throw new RuntimeException("Error reading file from path: " + folderPath);
        }
    }

}
