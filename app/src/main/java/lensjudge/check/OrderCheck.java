package lensjudge.check;

import lensjudge.execution.ExecutionResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The OrderCheck class implements the OutputComparator interface
 * and provides a method to compare the program output with the expected output.
 * The comparison checks if both outputs contain the same lines, regardless of order.
 */
public class OrderCheck implements OutputComparator{

    /**
     * Compares the program output and expected output by checking if both contain the same lines.
     * The comparison is order-independent.
     *
     * @param programOutput   The ExecutionResult object containing the program's output.
     * @param expectedOutput  The ExecutionResult object containing the expected output.
     * @return true if both outputs contain the same lines, false otherwise.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public boolean compare(ExecutionResult programOutput, ExecutionResult expectedOutput) throws IOException {
        ArrayList<String> programOutputLines = new ArrayList<>(List.of(programOutput.getOutput().split(" ")));
        ArrayList<String> expectedOutputLines = new ArrayList<>(List.of(expectedOutput.getOutput().split(" ")));

        if (programOutputLines.size() != expectedOutputLines.size()) {
            return false;
        }

        for (String line : expectedOutputLines) {
            if (!programOutputLines.contains(line)) {
                return false;
            }
        }
        return true;
    }
}
