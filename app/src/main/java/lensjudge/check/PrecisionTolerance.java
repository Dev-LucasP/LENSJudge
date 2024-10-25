package lensjudge.check;

import lensjudge.execution.ExecutionResult;

import java.io.IOException;

/**
 * The PrecisionTolerance class implements the OutputComparator interface
 * and provides a method to compare the program output with the expected output
 * within a specified tolerance.
 */
public class PrecisionTolerance implements OutputComparator {
    private double tolerance;

    /**
     * Constructs a new PrecisionTolerance with the specified tolerance.
     *
     * @param tolerance the tolerance within which the outputs are considered equal.
     */
    public PrecisionTolerance(double tolerance) {
        this.tolerance = tolerance;
    }

    /**
     * Compares the program output and expected output by checking if the numerical values
     * in both outputs are within the specified tolerance.
     *
     * @param programOutput   The ExecutionResult object containing the program's output.
     * @param expectedOutput  The ExecutionResult object containing the expected output.
     * @return true if the numerical values in the outputs are within the specified tolerance, false otherwise.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public boolean compare(ExecutionResult programOutput, ExecutionResult expectedOutput) throws IOException {
        String[] programLines = programOutput.getOutput().trim().split("\\s+");
        String[] expectedLines = expectedOutput.getOutput().trim().split("\\s+");

        if (programLines.length != expectedLines.length) {
            return false;
        }

        for (int i = 0; i < programLines.length; i++) {
            try {
                double programValue = Double.parseDouble(programLines[i]);
                double expectedValue = Double.parseDouble(expectedLines[i]);

                if (Math.abs(programValue - expectedValue) > tolerance) {
                    return false;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }

        return true;
    }
}

