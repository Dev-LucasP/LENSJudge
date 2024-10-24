package lensjudge.execution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.*;
import java.nio.file.Paths;

/**
 * The ProgramExecutor class is responsible for executing a given command
 * and capturing its output and error streams.
 */
public class ProgramExecutor {

    private String command;

    /**
     * Constructs a new ProgramExecutor with the specified command.
     *
     * @param command the command to be executed.
     */
    public ProgramExecutor(String command) {
        this.command = command;
    }

    /**
     * Executes the specified command and captures its output and error streams.
     *
     * @return an ExecutionResult object containing the exit code, standard output, and error output.
     * @throws IOException if an I/O error occurs during command execution.
     */
    public ExecutionResult execute() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));

        try {
            // Démarrer le processus
            Process process = processBuilder.start();

            // Lire la sortie standard
            BufferedReader stdOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            StringBuilder output = new StringBuilder();
            String line;

            // Capturer la sortie standard
            while ((line = stdOutput.readLine()) != null) {
                output.append(line).append("\n");
            }

            // Capturer la sortie d'erreur (le cas échéant)
            StringBuilder errorOutput = new StringBuilder();
            while ((line = stdError.readLine()) != null) {
                errorOutput.append(line).append("\n");
            }

            // Attendre que le processus termine
            int exitCode = process.waitFor();

            return new ExecutionResult(exitCode, output.toString(), errorOutput.toString());

        } catch (IOException | InterruptedException e) {
            throw new IOException("Failed to execute the program: " + e.getMessage(), e);
        }
    }
    
    public ExecutionResult executeWithInput(String inputFilePath) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));

        // Redirection de l'entrée standard à partir du fichier d'entrée
        processBuilder.redirectInput(new File(inputFilePath));

        // Redirection de la sortie standard et de la sortie d'erreur
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();

        // Lire la sortie du programme
        BufferedReader stdOutput = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = stdOutput.readLine()) != null) {
            output.append(line).append("\n");
        }

        // Attendre la fin du processus
        int exitCode = process.waitFor();

        return new ExecutionResult(exitCode, output.toString(), "");
    }

}
