package lensjudge.check;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lensjudge.execution.ExecutionResult;
import lensjudge.execution.ProgramExecutor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CaseAndSpaceComparisonTest {
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
    void testCaseAndSpaceInsensitive() throws IOException, InterruptedException {
        // Créer des fichiers Python
        String pythonScriptFile1 = createSourceFile("hello1.py", "print('  HeLLo from    Python!  ')");
        String pythonScriptFile2 = createSourceFile("hello2.py", "print('hello   from python!')");

        // Exécuter les scripts Python
        ProgramExecutor executor1 = new ProgramExecutor("python3 " + pythonScriptFile1);
        ExecutionResult result1 = executor1.execute();

        ProgramExecutor executor2 = new ProgramExecutor("python3 " + pythonScriptFile2);
        ExecutionResult result2 = executor2.execute();

        // Comparaison insensible à la casse et aux espaces
        CaseAndSpaceComparison comparison = new CaseAndSpaceComparison();
        boolean res = comparison.compare(result1, result2);

        // Vérifier les résultats
        assertTrue(res, "The Python scripts should be considered the same (case and space insensitive).");
    }
    
    @Test
    void testCaseAndSpaceInsensitiveFailure() throws IOException, InterruptedException {
        // Créer des fichiers Python
        String pythonScriptFile1 = createSourceFile("hello1.py", "print('  HeLao from    Python!  ')");
        String pythonScriptFile2 = createSourceFile("hello2.py", "print('hello   from python!')");

        // Exécuter les scripts Python
        ProgramExecutor executor1 = new ProgramExecutor("python3 " + pythonScriptFile1);
        ExecutionResult result1 = executor1.execute();

        ProgramExecutor executor2 = new ProgramExecutor("python3 " + pythonScriptFile2);
        ExecutionResult result2 = executor2.execute();

        // Comparaison insensible à la casse et aux espaces
        CaseAndSpaceComparison comparison = new CaseAndSpaceComparison();
        boolean res = comparison.compare(result1, result2);

        // Vérifier les résultats
        assertFalse(res, "The Python scripts should be considered the same (case and space insensitive).");
    }


}
