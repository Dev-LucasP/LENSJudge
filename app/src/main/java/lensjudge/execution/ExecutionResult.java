package lensjudge.execution;

public class ExecutionResult {
    private int exitCode;
    private String output;
    private String errorOutput;

    public ExecutionResult(int exitCode, String output, String errorOutput) {
        this.exitCode = exitCode;
        this.output = output;
        this.errorOutput = errorOutput;
    }

    public int getExitCode() {
        return exitCode;
    }

    public String getOutput() {
        return output;
    }

    public String getErrorOutput() {
        return errorOutput;
    }

    // Méthode utilitaire pour vérifier si l'exécution s'est bien passée
    public boolean isSuccess() {
        return exitCode == 0;
    }

    @Override
    public String toString() {
        return "Exit Code: " + exitCode + "\nOutput: " + output + "\nError Output: " + errorOutput;
    }
}
