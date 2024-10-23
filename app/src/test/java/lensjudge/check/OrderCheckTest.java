package lensjudge.check;

import lensjudge.execution.ExecutionResult;
import lensjudge.execution.ProgramExecutor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class OrderCheckTest {
    private String tempDir;

    @BeforeEach
    void setUp() {
        // Create a temporary directory for test files
        tempDir = System.getProperty("java.io.tmpdir") + "/execution_tests/";
        new File(tempDir).mkdirs();
    }

    private String createSourceFile(String filename, String content) throws IOException {
        String path = tempDir + filename;
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(content);
        }
        return path;
    }

    @Test
    void testWrongPythonScriptExecution() throws IOException {
        String pythonScriptFile1 = createSourceFile("hello.py",
                "print('1 2 3')");

        String pythonScriptFile2 = createSourceFile("hello2.py",
                "print('4 3 2 1 5')");

        // Exécuter le script Python
        ProgramExecutor executor1 = new ProgramExecutor("python3 " + pythonScriptFile1);
        ExecutionResult result1 = executor1.execute();

        ProgramExecutor executor2 = new ProgramExecutor("python3 " + pythonScriptFile2);
        ExecutionResult result2 = executor2.execute();

        OrderCheck comparison = new OrderCheck();
        boolean res = comparison.compare(result1, result2);

        // Verify the results
        assertFalse(res, "The Python script is same");
    }

    @Test
    void testRightPythonScriptExecution() throws IOException {
        String pythonScriptFile1 = createSourceFile("hello.py",
                "print('1 2 3 4')");

        String pythonScriptFile2 = createSourceFile("hello2.py",
                "print('4 3 2 1')");

        // Exécuter le script Python
        ProgramExecutor executor1 = new ProgramExecutor("python3 " + pythonScriptFile1);
        ExecutionResult result1 = executor1.execute();

        ProgramExecutor executor2 = new ProgramExecutor("python3 " + pythonScriptFile2);
        ExecutionResult result2 = executor2.execute();

        OrderCheck comparison = new OrderCheck();
        boolean res = comparison.compare(result1, result2);

        // Verify the results
        assertFalse(res, "The Python script is same");
    }
}