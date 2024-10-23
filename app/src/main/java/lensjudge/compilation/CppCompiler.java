package lensjudge.compilation;

import java.io.IOException;
/**
 * The CppCompiler class extends the CompilerStrategy class and provides methods
 * to check the file extension, deduce the binary file name, and compile C++ source files.
 */
public class CppCompiler extends CompilerStrategy {

    /**
     * Constructs a new CppCompiler with the specified source file.
     *
     * @param sourceFile the path to the C++ source file to be compiled.
     */
    public CppCompiler(String sourceFile) {
        super(sourceFile);
    }

    /**
     * Checks if the source file has a .cpp, .cc, or .cxx extension.
     *
     * @return true if the source file ends with .cpp, .cc, or .cxx, false otherwise.
     */
    @Override
    public boolean checkFileExtension() {
        return sourceFile.endsWith(".cpp") || sourceFile.endsWith(".cc") || sourceFile.endsWith(".cxx");
    }

    /**
     * Deduces the binary file name from the source file name by replacing the .cpp, .cc, or .cxx extension with .out.
     *
     * @param sourceFile the path to the C++ source file.
     * @return the deduced binary file name with a .out extension.
     */
    @Override
    public String deduceBinaryFile(String sourceFile) {
        return sourceFile.replace(".cpp", ".out").replace(".cc", ".out").replace(".cxx", ".out");
    }

    /**
     * Compiles the C++ source file using the g++ compiler.
     *
     * @return true if the compilation is successful, false otherwise.
     * @throws IOException if an I/O error occurs during compilation.
     * @throws InterruptedException if the compilation process is interrupted.
     * @throws IllegalArgumentException if the source file does not have a .cpp, .cc, or .cxx extension.
     */
    @Override
    public boolean compile() throws IOException, InterruptedException {
        if (!checkFileExtension()) {
            throw new IllegalArgumentException("Invalid C++ file extension");
        }

        String[] command = { "g++", sourceFile, "-o", deduceBinaryFile(sourceFile) };
        return runCommand(command);
    }
}