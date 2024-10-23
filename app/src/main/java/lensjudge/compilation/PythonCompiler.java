package lensjudge.compilation;

import java.io.IOException;

/**
 * The PythonCompiler class extends the CompilerStrategy class and provides methods
 * to check the file extension, deduce the binary file name, and check the syntax of Python source files.
 */
public class PythonCompiler extends CompilerStrategy {

    /**
     * Constructs a new PythonCompiler with the specified source file.
     *
     * @param sourceFile the path to the Python source file to be checked.
     */
    public PythonCompiler(String sourceFile) {
        super(sourceFile);
    }

    /**
     * Checks if the source file has a .py extension.
     *
     * @return true if the source file ends with .py, false otherwise.
     */
    @Override
    protected boolean checkFileExtension() {
        return sourceFile.endsWith(".py");
    }

    /**
     * Returns the source file itself as Python does not compile to a binary file.
     *
     * @param sourceFile the path to the Python source file.
     * @return the source file itself.
     */
    @Override
    public String deduceBinaryFile(String sourceFile) {
        return sourceFile;  // Python does not compile, it returns the source file itself
    }

    /**
     * Checks the syntax of the Python source file using the python3 interpreter.
     *
     * @return true if the syntax check is successful, false otherwise.
     * @throws IOException if an I/O error occurs during the syntax check.
     * @throws InterruptedException if the syntax check process is interrupted.
     * @throws IllegalArgumentException if the source file does not have a .py extension.
     */
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
