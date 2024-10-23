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

    // Utility method to create an output file
    private String createOutputFile(String filename, String content) throws IOException {
        String path = tempDir + filename;
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(content);
        }
        return path;
    }

    @Test
    void testPythonScriptExecution() throws IOException {
        // Créer un fichier source Python
        String pythonScriptFile1 = createSourceFile("hello.py", 
            "print('Hello from Python!')");
        
        String pythonScriptFile2 = createSourceFile("hello.py", 
                "print('Hello fro Python!')");
        
        // Exécuter le script Python
        ProgramExecutor executor1 = new ProgramExecutor("python3 " + pythonScriptFile1);
        ExecutionResult result1 = executor1.execute();
        
        ProgramExecutor executor2 = new ProgramExecutor("python3 " + pythonScriptFile2);
        ExecutionResult result2 = executor2.execute();
        
        StrictComparison comparison = new StrictComparison();
        boolean res = comparison.compare(result1, result2);
        
        // Vérifier les résultats
        assertTrue(res, "The Python script is same");
    }
}
