package lensjudge.check;

import lensjudge.execution.ExecutionResult;

import java.io.IOException;
import java.util.List;
/**
 * The MultipleSolutionsComparison class implements the OutputComparator interface
 * and provides a method to compare the program output against a list of valid solutions.
 */
public class MultipleSolutionsComparison implements OutputComparator {
    private List<String> validSolutions;
    /**
     * Constructs a new MultipleSolutionsComparison with the specified list of valid solutions.
     *
     * @param validSolutions the list of valid solutions to compare against.
     */
    public MultipleSolutionsComparison(List<String> validSolutions) {
        this.validSolutions = validSolutions;
    }
    /**
     * Compares the program output against the list of valid solutions.
     *
     * @param programOutput   The ExecutionResult object containing the program's output.
     * @param expectedOutput  The ExecutionResult object containing the expected output.
     * @return true if the program output is one of the valid solutions, false otherwise.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public boolean compare(ExecutionResult programOutput, ExecutionResult expectedOutput) throws IOException {
        String programSolution = programOutput.getOutput().trim();
        return validSolutions.contains(programSolution);
    }
}
