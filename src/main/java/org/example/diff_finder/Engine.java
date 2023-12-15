package org.example.diff_finder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Engine {

    public static final int COEFFICIENT_OF_SIMILAR = 50;

    public static List<String> firstListDiffs;
    public static List<String> secondListDiffs;
    public static String fileName1 = Path.of(App.firstFileName).getFileName().toString();
    public static String fileName2 = Path.of(App.secondFileName).getFileName().toString();
    public static void run(String filename1, String filename2) {
        var path1 = Paths.get(filename1).toAbsolutePath().normalize();
        var path2 = Paths.get(filename2).toAbsolutePath().normalize();

        try {
            var firstAllStrings = Files.readAllLines(path1);
            var secondAllStrings = Files.readAllLines(path2);
            firstListDiffs = new ArrayList<>(firstAllStrings);
            firstListDiffs.removeAll(secondAllStrings);
            secondListDiffs = new ArrayList<>(secondAllStrings);
            secondListDiffs.removeAll(firstAllStrings);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(buildResult(firstListDiffs, secondListDiffs));
    }

    public static StringBuilder printDifferences(List<String> list1, List<String> list2) {
        StringBuilder sb =
                new StringBuilder(fileName1 + " contains packages which are not in " + fileName2).append(":\n");
        for (var line : list1) {
            sb.append("    ").append(line).append("\n");
        }
        sb.append("\n")
                .append(fileName2)
                .append(" contains packages which are not in ")
                .append(fileName1)
                .append(":\n");

        for (var line : list2) {
            sb.append("    ").append(line).append("\n");
        }
        return sb;
    }

    public static StringBuilder findPartialMatches(List<String> list1, List<String> list2) {
        List<String> tmp1 = new ArrayList<>(firstListDiffs);
        List<String> tmp2 = new ArrayList<>(secondListDiffs);
        StringBuilder sb = new StringBuilder();
        for (var firstLine : list1.size() > list2.size() ? list1 : list2) {
            for (var secondLine : list1.size() > list2.size() ? list2 : list1) {
                if (isSimilar(firstLine, secondLine)) {
                    sb.append(sb.isEmpty() ? "version differences" : "");
                    tmp1.remove(firstLine);
                    tmp1.remove(secondLine);
                    tmp2.remove(firstLine);
                    tmp2.remove(secondLine);
                    sb.append("    ")
                            .append("\n")
                            .append(firstLine)
                            .append(" - ")
                            .append(secondLine);
                }
            }
        }
        firstListDiffs = tmp1;
        secondListDiffs = tmp2;
        return sb;
    }

    public static boolean isSimilar(String s1, String s2) {
        var byteEqualCounter = 0;
        var bytes1 = s1.toCharArray();
        var bytes2 = s2.toCharArray();
        var maxLength = COEFFICIENT_OF_SIMILAR * Math.min(s1.length(), s2.length()) / 100;

        while (byteEqualCounter < maxLength) {
            if (byteEqualCounter == bytes1.length ||
                    byteEqualCounter == bytes2.length ||
                    bytes1[byteEqualCounter] != bytes2[byteEqualCounter]) {
                return false;
            }
            byteEqualCounter++;
        }
        return true;
    }

    public static String buildResult(List<String> list1, List<String> list2) {
        return printDifferences(list1, list2)
                .append("\n")
                .append(findPartialMatches(list1, list2)).toString();
    }
}
