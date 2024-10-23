import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class CCompiler {

    /**
     * Compiles the given C source file into an executable.
     *
     * @param sourceFilePath the path to the C source file
     * @param executablePath the path to the output executable
     * @throws InvalidCSourceFileException if the file is not a C source file
     * @return true if the compilation is successful, false otherwise
     */
    public boolean compile(String sourceFilePath, String executablePath) throws InvalidCSourceFileException {
        if (!isCSourceFile(sourceFilePath)) {
            throw new InvalidCSourceFileException("Error: The file is not a C source file.");
        }

        ProcessBuilder processBuilder = new ProcessBuilder(
                "gcc", sourceFilePath, "-o", executablePath);
        return runProcess(processBuilder);
    }

    /**
     * Executes the given executable file.
     *
     * @param executablePath the path to the executable file
     * @return the output of the execution
     * @throws IOException if an I/O error occurs
     */
    public String execute(String executablePath) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(executablePath);
        return getProcessOutput(processBuilder);
    }

    private boolean runProcess(ProcessBuilder processBuilder) {
        try {
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            return exitCode == 0;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String getProcessOutput(ProcessBuilder processBuilder) throws IOException {
        Process process = processBuilder.start();
        StringBuilder output = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append(System.lineSeparator());
            }
        }
        return output.toString();
    }

    private boolean isCSourceFile(String filePath) {
        File file = new File(filePath);
        return file.isFile() && filePath.endsWith(".c");
    }
}