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
    void testCCompilerSuccess() throws IOException, InterruptedException {
        String sourcePath = createSourceFile("test.c", "#include <stdio.h>\nint main() { printf(\"Hello, C!\"); return 0; }");
        CCompiler compiler = new CCompiler(sourcePath);

        assertTrue(compiler.compile(), "C program should compile successfully.");
        assertTrue(new File(tempDir + "test.out").exists(), "Binary file should be created.");
    }

    @Test
    void testCCompilerFailure() throws IOException, InterruptedException {
        String sourcePath = createSourceFile("test.c", "#include <stdio.h>\nint main() { syntax_error! return 0; }");
        CCompiler compiler = new CCompiler(sourcePath);

        assertFalse(compiler.compile(), "C program should fail to compile due to syntax error.");
    }

    @Test
    void testCCompilerWrongExtension() {
        CCompiler compiler = new CCompiler("test.txt");

        assertThrows(IllegalArgumentException.class, compiler::compile, "Should throw exception for wrong file extension.");
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
    
    @Test
    void testPythonCompilerSuccess() throws IOException, InterruptedException {
        String sourcePath = createSourceFile("test.py", "print('Hello, Python!')");
        PythonCompiler compiler = new PythonCompiler(sourcePath);

        assertTrue(compiler.compile(), "Python syntax should be valid.");
    }

    @Test
    void testPythonCompilerFailure() throws IOException, InterruptedException {
        String sourcePath = createSourceFile("test.py", "print('Hello, Python!'\n");
        PythonCompiler compiler = new PythonCompiler(sourcePath);

        assertFalse(compiler.compile(), "Python script should contain syntax errors.");
    }

    @Test
    void testJavaCompilerSuccess() throws IOException, InterruptedException {
        String sourcePath = createSourceFile("test.java", "public class test { public static void main(String[] args) { System.out.println(\"Hello World\"); } }");
        JavaCompiler compiler = new JavaCompiler(sourcePath);

        assertTrue(compiler.compile(), "Java program should compile successfully.");
        assertTrue(new File(sourcePath.replace(".java", ".class")).exists(), "Class file should be created.");
    }

    @Test
    void testJavaCompilerFailure() throws IOException, InterruptedException {
        String sourcePath = createSourceFile("test.java", "public class test { public static void main(String[] args) { System.out.println(\"Hello World\") } }");
        JavaCompiler compiler = new JavaCompiler(sourcePath);

        assertFalse(compiler.compile(), "Java program should fail to compile due to syntax error.");
    }
}
