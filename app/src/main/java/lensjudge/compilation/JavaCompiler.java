package lensjudge.compilation;

import lensjudge.process.AdapterProcess;

import java.io.IOException;

public class JavaCompiler {
    private final String sourcePath;

    public JavaCompiler(String sourcePath) throws IllegalArgumentException {
        this.sourcePath = sourcePath;
        if (!sourcePath.endsWith(".java")) {
            throw new IllegalArgumentException("Source file must end with .java");
        }
    }

    public boolean compile() throws IOException, InterruptedException {
        AdapterProcess adapterProcess = new AdapterProcess();
        adapterProcess.createProcessus();
        adapterProcess.execute(new String[]{"javac", sourcePath});
        adapterProcess.getProcess().waitFor();
        return adapterProcess.getProcess().exitValue() == 0;
    }
}
