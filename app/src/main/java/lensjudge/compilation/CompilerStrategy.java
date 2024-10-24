package lensjudge.compilation;

import java.io.IOException;

/**
 * Abstract class to define the compilation strategy.
 * This class provides a template for specific compiler strategies.
 */
public abstract class CompilerStrategy {
    protected String sourceFile;
    protected String binaryFile;

    /**
     * Constructs a new CompilerStrategy with the specified source file.
     * The binary file name is deduced from the source file name.
     *
     * @param sourceFile the path to the source file to be compiled.
     */
    protected CompilerStrategy(String sourceFile) {
        this.sourceFile = sourceFile;
        this.binaryFile = deduceBinaryFile(sourceFile);
    }

    /**
     * Checks the source file extension.
     *
     * @return true if the source file has the correct extension, false otherwise.
     */
    protected abstract boolean checkFileExtension();

    /**
     * Deduces the name of the binary file from the source file.
     *
     * @param sourceFile the path to the source file.
     * @return the deduced binary file name.
     */
    protected abstract String deduceBinaryFile(String sourceFile);

    /**
     * Compiles the program.
     *
     * @return true if the compilation is successful, false otherwise.
     * @throws IOException if an I/O error occurs during compilation.
     * @throws InterruptedException if the compilation process is interrupted.
     */
    public abstract boolean compile() throws IOException, InterruptedException;

    /**
     * Executes a shell command.
     *
     * @param command the command to be executed.
     * @return true if the command executes successfully, false otherwise.
     * @throws IOException if an I/O error occurs during command execution.
     * @throws InterruptedException if the command execution is interrupted.
     */
    protected boolean runCommand(String[] command) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.redirectErrorStream(true); // Redirects errors to standard output
        Process process = processBuilder.start();
        
        // Waits for the end of the process
        int exitCode = process.waitFor();
        return exitCode == 0;  // If the output code is 0, compilation was successful.
    }
}