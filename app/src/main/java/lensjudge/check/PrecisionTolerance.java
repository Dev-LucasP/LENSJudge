package lensjudge.check;

import lensjudge.execution.ExecutionResult;

import java.io.IOException;

public class PrecisionTolerance implements OutputComparator {
    private double tolerance;

    public PrecisionTolerance(double tolerance) {
        this.tolerance = tolerance;
    }

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
                    return false; // Différence trop importante
                }
            } catch (NumberFormatException e) {
                return false; // Ce n'est pas un nombre
            }
        }

        return true;
    }
}

