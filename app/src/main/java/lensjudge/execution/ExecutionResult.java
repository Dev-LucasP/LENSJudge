package lensjudge.execution;

/**
 * The ExecutionResult class encapsulates the result of executing a command,
 * including the exit code, standard output, and error output.
 */
public class ExecutionResult {
    private int exitCode;
    private String output;
    private String errorOutput;

    /**
     * Constructs a new ExecutionResult with the specified exit code, output, and error output.
     *
     * @param exitCode the exit code of the executed command.
     * @param output the standard output of the executed command.
     * @param errorOutput the error output of the executed command.
     */
    public ExecutionResult(int exitCode, String output, String errorOutput) {
        this.exitCode = exitCode;
        this.output = output;
        this.errorOutput = errorOutput;
    }

    /**
     * Returns the exit code of the executed command.
     *
     * @return the exit code.
     */
    public int getExitCode() {
        return exitCode;
    }

    /**
     * Returns the standard output of the executed command.
     *
     * @return the standard output.
     */
    public String getOutput() {
        return output;
    }

    /**
     * Returns the error output of the executed command.
     *
     * @return the error output.
     */
    public String getErrorOutput() {
        return errorOutput;
    }

    /**
     * Checks if the execution was successful (i.e., the exit code is 0).
     *
     * @return true if the exit code is 0, false otherwise.
     */
    public boolean isSuccess() {
        return exitCode == 0;
    }

    /**
     * Returns a string representation of the execution result, including the exit code,
     * standard output, and error output.
     *
     * @return a string representation of the execution result.
     */
    @Override
    public String toString() {
        return "Exit Code: " + exitCode + "\nOutput: " + output + "\nError Output: " + errorOutput;
    }
}
