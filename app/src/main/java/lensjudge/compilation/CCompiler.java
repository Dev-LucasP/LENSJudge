package lensjudge.compilation;

import java.io.IOException;

/**
 * The CCompiler class extends the CompilerStrategy class and provides methods
 * to check the file extension, deduce the binary file name, and compile C source files.
 */
public class CCompiler extends CompilerStrategy {

    /**
     * Constructs a new CCompiler with the specified source file.
     *
     * @param sourceFile the path to the C source file to be compiled.
     */
    public CCompiler(String sourceFile) {
        super(sourceFile);
    }

    /**
     * Checks if the source file has a .c extension.
     *
     * @return true if the source file ends with .c, false otherwise.
     */
    @Override
    public boolean checkFileExtension() {
        return sourceFile.endsWith(".c");
    }

    /**
     * Deduces the binary file name from the source file name by replacing the .c extension with .out.
     *
     * @param sourceFile the path to the C source file.
     * @return the deduced binary file name with a .out extension.
     */
    @Override
    public String deduceBinaryFile(String sourceFile) {
        return sourceFile.replace(".c", ".out");
    }

    /**
     * Compiles the C source file using the gcc compiler.
     *
     * @return true if the compilation is successful, false otherwise.
     * @throws IOException if an I/O error occurs during compilation.
     * @throws InterruptedException if the compilation process is interrupted.
     * @throws IllegalArgumentException if the source file does not have a .c extension.
     */
    @Override
    public boolean compile() throws IOException, InterruptedException {
        if (!checkFileExtension()) {
            throw new IllegalArgumentException("Invalid C file extension");
        }

        String[] command = { "gcc", sourceFile, "-o", deduceBinaryFile(sourceFile) };
        return runCommand(command);
    }
}