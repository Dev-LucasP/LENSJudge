package lensjudge.check;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lensjudge.execution.ExecutionResult;
import lensjudge.execution.ProgramExecutor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MultipleSolutionsComparisonTest {
    private String tempDir;

    @BeforeEach
    void setUp() throws IOException {
        // Create a temporary directory for test files
        tempDir = System.getProperty("java.io.tmpdir") + "/execution_tests/";
        new File(tempDir).mkdirs();
    }

    // Utility method to create a source file
    private String createSourceFile(String filename, String content) throws IOException {
        String path = tempDir + filename;
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(content);
        }
        return path;
    }

    // Utility method to create an output file
    private String createOutputFile(String filename, String content) throws IOException {
        String path = tempDir + filename;
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(content);
        }
        return path;
    }
    
    @Test
    void testMultipleSolutionsComparison() throws IOException, InterruptedException {
        // Créer un fichier source Python
        String pythonScriptFile = createSourceFile("solution.py", "print('Solution B')");

        // Exécuter le script Python
        ProgramExecutor executor = new ProgramExecutor("python3 " + pythonScriptFile);
        ExecutionResult result = executor.execute();

        // Liste des solutions acceptées
        List<String> validSolutions = Arrays.asList("Solution A", "Solution B", "Solution C");

        // Comparaison avec acceptation de plusieurs solutions
        MultipleSolutionsComparison comparison = new MultipleSolutionsComparison(validSolutions);
        boolean res = comparison.compare(result, null); // Pas de fichier de comparaison attendu

        // Vérifier les résultats
        assertTrue(res, "The Python script output should match one of the valid solutions.");
    }
    
    @Test
    void testMultipleSolutionsComparisonFailure() throws IOException, InterruptedException {
        // Créer un fichier source Python
        String pythonScriptFile = createSourceFile("solution.py", "print('Solution L')");

        // Exécuter le script Python
        ProgramExecutor executor = new ProgramExecutor("python3 " + pythonScriptFile);
        ExecutionResult result = executor.execute();

        // Liste des solutions acceptées
        List<String> validSolutions = Arrays.asList("Solution A", "Solution B", "Solution C");

        // Comparaison avec acceptation de plusieurs solutions
        MultipleSolutionsComparison comparison = new MultipleSolutionsComparison(validSolutions);
        boolean res = comparison.compare(result, null); // Pas de fichier de comparaison attendu

        // Vérifier les résultats
        assertFalse(res, "The Python script output should match one of the valid solutions.");
    }


}
