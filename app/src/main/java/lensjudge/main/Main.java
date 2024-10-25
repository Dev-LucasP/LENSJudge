package lensjudge.main;

import lensjudge.problem.Problem;
import lensjudge.problem.ProblemBuilder;
import lensjudge.problem.TestCase;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2 || args.length > 3) {
            System.out.println("Usage:");
            System.out.println("  lensjudge <sourceFile> <test.in> <test.ans>  # For a single test");
            System.out.println("  lensjudge <sourceFile> <test-dir>            # For a directory of tests");
            return;
        }

        String sourceFile = new File(args[0]).getAbsolutePath();

        if (args.length == 3) {

            String inputFile = new File(args[1]).getAbsolutePath();
            String expectedOutputFile = new File(args[2]).getAbsolutePath();

            try {
                Runner runner = RunnerBuilder.createRunner(sourceFile, inputFile, expectedOutputFile).build();
                String result = runner.run();

                System.out.println(result);

            } catch (IOException e) {
                System.out.println("An error occurred: " + e.getMessage());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("The operation was interrupted: " + e.getMessage());
            }

        } else if (args.length == 2) {
            String testDirectory = new File(args[1]).getAbsolutePath();

            try {
                Problem problem = ProblemBuilder.build(testDirectory);

                for (TestCase testCase : problem) {
                    System.out.println("Running test case with input: " + testCase.getInputFile());

                    Runner runner = RunnerBuilder.createRunner(sourceFile, testCase.getInputFile(), testCase.getExpectedOutputFile()).build();
                    String result = runner.run();

                    if (!result.equals("\u001B[32mCORRECT\u001B[0m")) {
                        System.out.println(result);
                        return;
                    }
                }

                // Si tous les tests sont corrects
                System.out.println("\u001B[32mCORRECT\u001B[0m");

            } catch (IOException e) {
                System.out.println("An error occurred: " + e.getMessage());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("The operation was interrupted: " + e.getMessage());
            }
        } else {
            System.out.println("Invalid usage. Expected 2 or 3 arguments.");
            System.out.println("Usage:");
            System.out.println("  lensjudge <sourceFile> <test.in> <test.ans>  # For a single test");
            System.out.println("  lensjudge <sourceFile> <test-dir>            # For a directory of tests");
        }
    }
}
