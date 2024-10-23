package lensjudge.compilation;


import java.io.IOException;

public class JavaCompiler extends CompilerStrategy {

    public JavaCompiler(String sourceFile) throws IllegalArgumentException {
        super(sourceFile);
    }

    @Override
    public boolean checkFileExtension() {
        return sourceFile.endsWith(".java");
    }

    @Override
    public String deduceBinaryFile(String sourceFile) {
        return sourceFile.replace(".java", ".class");
    }

    public boolean compile() throws IOException, InterruptedException {
        if (!checkFileExtension()) {
            throw new IllegalArgumentException("Invalid Java file extension");
        }

        String[] command = {"javac", sourceFile};
        return runCommand(command);
    }
}
