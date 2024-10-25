package lensjudge.problem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProblemBuilder {

    // Méthode pour construire un Problem à partir d'un répertoire contenant des fichiers de test
    public static Problem build(String directoryPath) {
        File dir = new File(directoryPath);
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException("Provided path is not a directory.");
        }

        List<TestCase> testCases = new ArrayList<>();

        // Parcourir tous les fichiers dans le répertoire
        File[] testFiles = dir.listFiles((d, name) -> name.endsWith(".in"));
        if (testFiles != null) {
            for (File testFile : testFiles) {
                String baseName = testFile.getName().replace(".in", "");
                String inputFilePath = testFile.getAbsolutePath();
                String expectedOutputFilePath = new File(dir, baseName + ".ans").getAbsolutePath();

                // Créer un TestCase pour chaque couple .in et .ans
                TestCase testCase = new TestCase(inputFilePath, expectedOutputFilePath);
                testCases.add(testCase);
            }
        }

        // Retourner le problème contenant tous les cas de test
        return new Problem(testCases);
    }
}
