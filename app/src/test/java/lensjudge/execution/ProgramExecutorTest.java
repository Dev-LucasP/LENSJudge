package lensjudge.execution;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import lensjudge.compilation.*;

class ProgramExecutorTest {

    private String tempDir;

    @BeforeEach
    void setUp() {
        tempDir = System.getProperty("java.io.tmpdir") + "/execution_tests/";
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
void testCProgramExecution() throws IOException, InterruptedException {
    String cSourceFile = createSourceFile("hello.c", """
        #include <stdio.h>
        int main() {
            printf("Hello from C!\\n");
            return 0;
        }
        """);

    CCompiler cCompiler = new CCompiler(cSourceFile);
    cCompiler.compile();

    String binaryFile = cCompiler.deduceBinaryFile(cSourceFile);
    ProgramExecutor executor = new ProgramExecutor(binaryFile);
    ExecutionResult result = executor.execute();

    assertTrue(result.isSuccess(), "The C program should execute successfully.");
    assertEquals("Hello from C!\n", result.getOutput(), "The C program should print the correct message.");
    assertEquals("", result.getErrorOutput(), "There should be no errors.");
}

	@Test
	void testCppProgramExecution() throws IOException, InterruptedException {
	    String cppSourceFile = createSourceFile("hello.cpp", """
	        #include <iostream>
	        int main() {
	            std::cout << "Hello from C++!" << std::endl;
	            return 0;
	        }
	        """);
	
	    CppCompiler cppCompiler = new CppCompiler(cppSourceFile);
	    cppCompiler.compile();
	
	    String binaryFile = cppCompiler.deduceBinaryFile(cppSourceFile);
	    ProgramExecutor executor = new ProgramExecutor(binaryFile);
	    ExecutionResult result = executor.execute();
	
	    assertTrue(result.isSuccess(), "The C++ program should execute successfully.");
	    assertEquals("Hello from C++!\n", result.getOutput(), "The C++ program should print the correct message.");
	    assertEquals("", result.getErrorOutput(), "There should be no errors.");
	}
	
	@Test
	void testPythonScriptExecution() throws IOException, InterruptedException {
	    String pythonScriptFile = createSourceFile("hello.py", """
	        print('Hello from Python!')
	        """);
	
	    ProgramExecutor executor = new ProgramExecutor("python3 " + pythonScriptFile);
	    ExecutionResult result = executor.execute();
	    assertTrue(result.isSuccess(), "The Python script should execute successfully.");
	    assertEquals("Hello from Python!\n", result.getOutput(), "The Python script should print the correct message.");
	    assertEquals("", result.getErrorOutput(), "There should be no errors.");
	}

}

