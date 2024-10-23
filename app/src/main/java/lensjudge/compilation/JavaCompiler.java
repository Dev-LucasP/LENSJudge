package lensjudge.compilation;


import java.io.IOException;
/**
 * The JavaCompiler class extends the CompilerStrategy class and provides methods
 * to check the file extension, deduce the binary file name, and compile Java source files.
 */
public class JavaCompiler extends CompilerStrategy {

    /**
     * Constructs a new JavaCompiler with the specified source file.
     *
     * @param sourceFile the path to the Java source file to be compiled.
     * @throws IllegalArgumentException if the source file does not have a .java extension.
     */
    public JavaCompiler(String sourceFile) throws IllegalArgumentException {
        super(sourceFile);
    }

    /**
     * Checks if the source file has a .java extension.
     *
     * @return true if the source file ends with .java, false otherwise.
     */
    @Override
    public boolean checkFileExtension() {
        return sourceFile.endsWith(".java");
    }

    /**
     * Deduces the binary file name from the source file name by replacing the .java extension with .class.
     *
     * @param sourceFile the path to the Java source file.
     * @return the deduced binary file name with a .class extension.
     */
    @Override
    protected String deduceBinaryFile(String sourceFile) {
        return sourceFile.replace(".java", ".class");
    }

    /**
     * Compiles the Java source file using the javac compiler.
     *
     * @return true if the compilation is successful, false otherwise.
     * @throws IOException if an I/O error occurs during compilation.
     * @throws InterruptedException if the compilation process is interrupted.
     * @throws IllegalArgumentException if the source file does not have a .java extension.
     */
    public boolean compile() throws IOException, InterruptedException {
        if (!checkFileExtension()) {
            throw new IllegalArgumentException("Invalid Java file extension");
        }

        String[] command = {"javac", sourceFile};
        return runCommand(command);
    }
}
