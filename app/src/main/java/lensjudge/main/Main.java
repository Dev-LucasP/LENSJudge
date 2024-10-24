package lensjudge.main;

import lensjudge.problem.Problem;
import lensjudge.problem.ProblemBuilder;
import lensjudge.problem.TestCase;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage:");
            System.out.println("  lensjudge <sourceFile> <test.in> <test.ans>  # For a single test");
            System.out.println("  lensjudge <sourceFile> <test-dir>            # For a directory of tests");
            return;
        }

        // Le fichier source est toujours le premier argument
        String sourceFile = new File(args[0]).getAbsolutePath();

        if (args.length == 3) {
            // Cas 1 : Un seul test avec <test.in> et <test.ans>
            String inputFile = new File(args[1]).getAbsolutePath();
            String expectedOutputFile = new File(args[2]).getAbsolutePath();

            try {
                // Créer un Runner pour un seul test
                Runner runner = RunnerBuilder.createRunner(sourceFile, inputFile, expectedOutputFile).build();
                String result = runner.run();

                // Afficher le résultat (y compris les différences si applicable)
                System.out.println(result);

            } catch (IOException | InterruptedException e) {
                System.out.println("An error occurred: " + e.getMessage());
            }

        } else if (args.length == 2) {
            // Cas 2 : Un répertoire de tests avec <test-dir>
            String testDirectory = new File(args[1]).getAbsolutePath();

            try {
                // Construire un problème à partir du répertoire de test
                Problem problem = ProblemBuilder.build(testDirectory);

                // Itérer sur chaque cas de test du problème
                for (TestCase testCase : problem) {
                    System.out.println("Running test case with input: " + testCase.getInputFile());

                    // Créer un Runner pour chaque test
                    Runner runner = RunnerBuilder.createRunner(sourceFile, testCase.getInputFile(), testCase.getExpectedOutputFile()).build();
                    String result = runner.run();

                    // Afficher le résultat et interrompre si une erreur est détectée
                    if (!result.equals("\u001B[32mCORRECT\u001B[0m")) {
                        System.out.println(result); // Afficher les différences si applicable
                        return;
                    }
                }

                // Si tous les tests sont corrects
                System.out.println("\u001B[32mCORRECT\u001B[0m");

            } catch (IOException | InterruptedException e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        } else {
            // Mauvaise utilisation
            System.out.println("Invalid usage. Expected 2 or 3 arguments.");
            System.out.println("Usage:");
            System.out.println("  lensjudge <sourceFile> <test.in> <test.ans>  # For a single test");
            System.out.println("  lensjudge <sourceFile> <test-dir>            # For a directory of tests");
        }
    }
}
