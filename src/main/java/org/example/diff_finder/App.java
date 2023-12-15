package org.example.diff_finder;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "DifFinder",
        version = "1.3",
        mixinStandardHelpOptions = true,
        description = "Compares two files and shows a differences.")
public class App implements Callable {

    @CommandLine.Parameters(
            paramLabel = "first",
            description = "path to first file")
    public static String firstFileName;

    @CommandLine.Parameters(
            paramLabel = "second",
            description = "path to second file")
    public static String secondFileName;

    public static void main(String[] args) {
        new CommandLine(new App()).execute(args);
    }

    @Override
    public Object call() {
        if (firstFileName == null || secondFileName == null || firstFileName.isBlank() || secondFileName.isBlank()) {
            System.out.println("Please, enter two files!");
            return null;
        }
        Engine.run(firstFileName, secondFileName);
        return null;

    }
}