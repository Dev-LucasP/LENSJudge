package lensjudge.compilation;

import java.io.IOException;

//Abstract class to define compilation strategy
abstract class CompilerStrategy {
    protected String sourceFile;
    protected String binaryFile;

    protected CompilerStrategy(String sourceFile) {
        this.sourceFile = sourceFile;
        this.binaryFile = deduceBinaryFile(sourceFile);
    }

    // Checks source file extension
    protected abstract boolean checkFileExtension();

    // Deducts the name of the binary file from the source file
    protected abstract String deduceBinaryFile(String sourceFile);

    // Abstract method to compile the program
    public abstract boolean compile() throws IOException, InterruptedException;
    
    // Method for executing a shell command
    protected boolean runCommand(String[] command) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.redirectErrorStream(true); // Redirects errors to standard output
        Process process = processBuilder.start();
        
        // Waits for the end of the process
        int exitCode = process.waitFor();
        return exitCode == 0;  // If the output code is 0, compilation was successful.
    }
}