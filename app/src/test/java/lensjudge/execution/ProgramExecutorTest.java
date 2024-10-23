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
    void setUp() throws IOException {
        // Crée un répertoire temporaire pour les fichiers de test
        tempDir = System.getProperty("java.io.tmpdir") + "/execution_tests/";
        new File(tempDir).mkdirs();
    }

    // Méthode utilitaire pour créer des fichiers binaires fictifs
    private String createBinaryFile(String filename, String content) throws IOException {
        String path = tempDir + filename;
        FileWriter writer = new FileWriter(path);
        writer.write(content);
        writer.close();
        // Rendre le fichier exécutable
        new File(path).setExecutable(true);
        return path;
    }
    
    // Méthode utilitaire pour créer un fichier source (C, C++, ou Python)
    private String createSourceFile(String filename, String content) throws IOException {
        String path = tempDir + filename;
        FileWriter writer = new FileWriter(path);
        writer.write(content);
        writer.close();
        return path;
    }
    
    @Test
    void testCProgramExecution() throws IOException, InterruptedException {
        // Créer un fichier source C
        String cSourceFile = createSourceFile("hello.c", 
            "#include <stdio.h>\n" +
            "int main() {\n" +
            "    printf(\"Hello from C!\\n\");\n" +
            "    return 0;\n" +
            "}");
        
        // Compiler le fichier C
        CCompiler cCompiler = new CCompiler(cSourceFile);
        cCompiler.compile();

        // Exécuter le binaire
        String binaryFile = cCompiler.deduceBinaryFile(cSourceFile);
        ProgramExecutor executor = new ProgramExecutor(binaryFile);
        ExecutionResult result = executor.execute();

        // Vérifier les résultats
        assertTrue(result.isSuccess(), "The C program should execute successfully.");
        assertEquals("Hello from C!\n", result.getOutput(), "The C program should print the correct message.");
        assertEquals("", result.getErrorOutput(), "There should be no errors.");
    }


    @Test
    void testCppProgramExecution() throws IOException, InterruptedException {
        // Créer un fichier source C++
        String cppSourceFile = createSourceFile("hello.cpp", 
            "#include <iostream>\n" +
            "int main() {\n" +
            "    std::cout << \"Hello from C++!\" << std::endl;\n" +
            "    return 0;\n" +
            "}");
        
        // Compiler le fichier C++
        CppCompiler cppCompiler = new CppCompiler(cppSourceFile);
        cppCompiler.compile();

        // Exécuter le binaire
        String binaryFile = cppCompiler.deduceBinaryFile(cppSourceFile);
        ProgramExecutor executor = new ProgramExecutor(binaryFile); // Le binaire compilé s'appelle "exe"
        ExecutionResult result = executor.execute();

        // Vérifier les résultats
        assertTrue(result.isSuccess(), "The C++ program should execute successfully.");
        assertEquals("Hello from C++!\n", result.getOutput(), "The C++ program should print the correct message.");
        assertEquals("", result.getErrorOutput(), "There should be no errors.");
    }
    
    @Test
    void testPythonScriptExecution() throws IOException {
        // Créer un fichier source Python
        String pythonScriptFile = createSourceFile("hello.py", 
            "print('Hello from Python!')");
        
        // Exécuter le script Python
        ProgramExecutor executor = new ProgramExecutor("python3 " + pythonScriptFile);
        ExecutionResult result = executor.execute();
        // Vérifier les résultats
        assertTrue(result.isSuccess(), "The Python script should execute successfully.");
        assertEquals("Hello from Python!\n", result.getOutput(), "The Python script should print the correct message.");
        assertEquals("", result.getErrorOutput(), "There should be no errors.");
    }
}

