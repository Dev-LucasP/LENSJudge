package lensjudge.compilation;

import lensjudge.process.AdapterProcess;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import java.io.FileWriter;

class TestJavaCompiler {

    @Test
    void compileWrongFormat() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new JavaCompiler("test"));
        assertEquals("Source file must end with .java", exception.getMessage());
    }

    @Test
    void TestJavaCompiler() throws IOException, InterruptedException {
        File file = new File("test.java");
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        writer.write("public class test { public static void main(String[] args) { System.out.println(\"Hello World\"); } }");
        writer.close();
        JavaCompiler javaCompiler = new JavaCompiler("test.java");
        assertTrue(javaCompiler.compile());
        file.delete();
    }
}