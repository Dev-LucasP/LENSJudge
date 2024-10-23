package lensjudge.compilation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class CompilerTest {

    private String tempDir;

    @BeforeEach
    void setUp() {
        tempDir = System.getProperty("java.io.tmpdir") + "/compile_tests/";
        new File(tempDir).mkdirs();
    }

    private String createSourceFile(String filename, String content) throws IOException {
        String path = tempDir + filename;
        FileWriter writer = new FileWriter(path);
        writer.write(content);
        writer.close();
        return path;
    }

    @Test
    void testCppCompilerSuccess() throws IOException, InterruptedException {
        String sourcePath = createSourceFile("test.cpp", "#include <iostream>\nint main() { std::cout << \"Hello, C++!\"; return 0; }");
        CppCompiler compiler = new CppCompiler(sourcePath);

        assertTrue(compiler.compile(), "C++ program should compile successfully.");
        assertTrue(new File(sourcePath.replace(".cpp", ".out")).exists(), "Binary file should be created.");
    }

    @Test
	void testCppCompilerFailure() throws IOException, InterruptedException {
        String sourcePath = createSourceFile("test.cpp", "#include <iostream>\nint main() { std::cout << \"Hello, C++!\"; return 0 }");
        CppCompiler compiler = new CppCompiler(sourcePath);

        assertFalse(compiler.compile(), "C++ program should compile successfully.");
	}
}
