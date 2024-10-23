import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class CCompilerTest {

    @Test
    public void testCompileSuccess() {
        CCompilateur compilateur = new CCompilateur();
        boolean result = compilateur.compile("test.c", "test.exe");
        assertTrue(result, "Compilation should succeed");
    }

    @Test
    public void testCompileFailure() {
        CCompilateur compilateur = new CCompilateur();
        boolean result = compilateur.compile("nonexistent.c", "test.exe");
        assertFalse(result, "Compilation should fail for nonexistent file");
    }

    @Test
    public void testExecuteSuccess() throws IOException {
        CCompilateur compilateur = new CCompilateur();
        compilateur.compile("test.c", "test.exe");
        String output = compilateur.execute("test.exe");
        assertNotNull(output, "Execution output should not be null");
    }

    @Test
    public void testExecuteFailure() {
        CCompilateur compilateur = new CCompilateur();
        assertThrows(IOException.class, () -> {
            compilateur.execute("nonexistent.exe");
        }, "Execution should fail for nonexistent executable");
    }
}