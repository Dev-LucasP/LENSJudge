package lensjudge.execution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProgramExecutor {

    private String command;

    public ProgramExecutor(String command) {
        this.command = command;
    }

    // Méthode pour exécuter un programme ou script
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

}
