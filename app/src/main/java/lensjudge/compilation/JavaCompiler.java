package lensjudge.compilation;

import lensjudge.process.AdapterProcess;

import java.io.IOException;

public class JavaCompiler {
    public static void compile(String sourcePath, String classPath) throws IOException {
        if (!sourcePath.endsWith(".java")) {
            throw new IllegalArgumentException("Source file must end with .java");
        }
        AdapterProcess adapterProcess = new AdapterProcess();
        adapterProcess.createProcessus();
        adapterProcess.execute(new String[] { "java", "-d", classPath, sourcePath });
    }
}
