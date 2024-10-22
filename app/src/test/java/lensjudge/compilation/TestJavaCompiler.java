package lensjudge.compilation;

import lensjudge.process.AdapterProcess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestJavaCompiler {

    private AdapterProcess adapterProcess;

/*    @BeforeEach
    void setUp() {
        adapterProcess = mock(AdapterProcess.class);
    }*/

    @Test
    void compileWrongFormat() {
        assertThrows(IllegalArgumentException.class, () -> JavaCompiler.compile("source.txt", "classPath"));
    }

    @Test
    void testCompile() throws IOException {
        JavaCompiler javaCompiler = new JavaCompiler();
        adapterProcess.createProcessus();
    }

}