package lensjudge.check;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lensjudge.execution.ExecutionResult;
import lensjudge.execution.ProgramExecutor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class StrictComparisonTest {
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

    
  
    @Test
    void testPrecisionTolerance() throws IOException {
        // Créer un fichier source Python
        String pythonScriptFile1 = createSourceFile("calc1.py", "print(3.14159)");
        String pythonScriptFile2 = createSourceFile("calc2.py", "print(3.14160)");

        // Exécuter les scripts Python
        ProgramExecutor executor1 = new ProgramExecutor("python3 " + pythonScriptFile1);
        ExecutionResult result1 = executor1.execute();

        ProgramExecutor executor2 = new ProgramExecutor("python3 " + pythonScriptFile2);
        ExecutionResult result2 = executor2.execute();

        // Comparaison avec tolérance
        PrecisionTolerance comparison = new PrecisionTolerance(0.0001);
        boolean res = comparison.compare(result1, result2);
        
        System.out.println(result1.getOutput());
        System.out.println(result2.getOutput());

        // Vérifier les résultats
        assertTrue(res, "The Python script should output values within the tolerance.");
    }
    
    @Test
    void testPrecisionToleranceFailure() throws IOException {
        // Créer des fichiers source Python différents
        String pythonScriptFile1 = createSourceFile("calc1.py", "print(4.14159)");
        String pythonScriptFile2 = createSourceFile("calc2.py", "print(3.14159)");

        // Exécuter les scripts Python
        ProgramExecutor executor1 = new ProgramExecutor("python3 " + pythonScriptFile1);
        ExecutionResult result1 = executor1.execute();

        ProgramExecutor executor2 = new ProgramExecutor("python3 " + pythonScriptFile2);
        ExecutionResult result2 = executor2.execute();
        
        System.out.println(result1.getOutput());
        System.out.println(result2.getOutput());

        // Comparaison avec tolérance
        PrecisionTolerance comparison = new PrecisionTolerance(0.0001);
        boolean res = comparison.compare(result1, result2);

        // Vérifier les résultats
        assertFalse(res, "The Python script outputs should not be within the tolerance.");
    }
    
    
}
