package lensjudge.compilation;

import java.io.IOException;

class PythonCompiler extends CompilerStrategy {

    public PythonCompiler(String sourceFile) {
        super(sourceFile);
    }

    @Override
    protected boolean checkFileExtension() {
        return sourceFile.endsWith(".py");
    }

    @Override
    protected String deduceBinaryFile(String sourceFile) {
        return sourceFile;  // Python does not compile, it returns the source file itself
    }

    @Override
    public boolean compile() throws IOException, InterruptedException {
        if (!checkFileExtension()) {
            throw new IllegalArgumentException("Invalid Python file extension");
        }

        // Command to check Python syntax
        String[] command = { "python3", "-m", "py_compile", sourceFile };
        return runCommand(command);
    }
}
