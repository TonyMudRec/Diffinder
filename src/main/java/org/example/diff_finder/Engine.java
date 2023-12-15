package org.example.diff_finder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.regex.Pattern;

public class Engine {

    public static final String FIRST_FILE_NAME = Path.of(App.firstFileName).getFileName().toString();
    public static final String SECOND_FILE_NAME = Path.of(App.secondFileName).getFileName().toString();

    public static void run(String filename1, String filename2) {
        var path1 = Paths.get(filename1).toAbsolutePath().normalize();
        var path2 = Paths.get(filename2).toAbsolutePath().normalize();
        var firstFileLines = new HashSet<String>();
        var secondFileLines = new HashSet<String>();
        var firstListDiffs = new HashSet<String>();
        var secondListDiffs = new HashSet<String>();

        try (BufferedReader br1 = new BufferedReader(new FileReader(String.valueOf(path1)));
             BufferedReader br2 = new BufferedReader(new FileReader(String.valueOf(path2)))) {
            String line;

            while ((line = br1.readLine()) != null) {
                firstFileLines.add(line);
            }
            while ((line = br2.readLine()) != null) {
                secondFileLines.add(line);
            }

            firstListDiffs = new HashSet<>(firstFileLines);
            firstListDiffs.removeAll(secondFileLines);
            secondListDiffs = new HashSet<>(secondFileLines);
            secondListDiffs.removeAll(firstFileLines);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(buildResult(firstListDiffs, secondListDiffs));
    }

    public static StringBuilder getBuiltDifferences(Set<String> set1, String firstFileName, String secondFileName) {
        StringBuilder sb =
                new StringBuilder("\n " + firstFileName + " contains packages which are not in " + secondFileName)
                        .append(":\n");
        for (var line : set1) {
            sb.append("    ").append(line).append("\n");
        }
        return sb;
    }

    public static StringBuilder findPartialMatches(Set<String> set1, Set<String> set2) {
        List<String> partialMatches = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (var firstLine : set1.size() > set2.size() ? set1 : set2) {
            for (var secondLine : set1.size() > set2.size() ? set2 : set1) {
                if (isSimilar(firstLine, secondLine)) {
                    partialMatches.add(firstLine);
                    partialMatches.add(secondLine);
                    sb.append(sb.isEmpty() ? "\n version differences" : "");
                    sb.append("    ")
                            .append("\n")
                            .append(FIRST_FILE_NAME)
                            .append(" - ")
                            .append(firstLine)
                            .append("\n")
                            .append(SECOND_FILE_NAME)
                            .append(" - ")
                            .append(secondLine);
                }
            }
        }
        partialMatches.forEach(s -> {
            set1.remove(s);
            set2.remove(s);
        });
        return sb;
    }

    public static boolean isSimilar(String s1, String s2) {
        Pattern pattern = Pattern.compile("[\\w-]*\\.}");
        return pattern.matcher(s1).toString().equals(pattern.matcher(s2).toString());
    }

    public static String buildResult(Set<String> set1, Set<String> set2) {
        return getBuiltDifferences(set1, FIRST_FILE_NAME, SECOND_FILE_NAME)
                .append(getBuiltDifferences(set2, SECOND_FILE_NAME, FIRST_FILE_NAME)
                        .append(findPartialMatches(set1, set2))).toString();
    }
}
