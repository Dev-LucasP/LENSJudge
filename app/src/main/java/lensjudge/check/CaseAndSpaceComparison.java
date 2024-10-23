package lensjudge.check;

import lensjudge.execution.ExecutionResult;

import java.io.IOException;
/**
 * The CaseAndSpaceComparison class implements the OutputComparator interface
 * and provides a method to compare two ExecutionResult objects by normalizing
 * their output strings. The normalization process includes trimming whitespace,
 * replacing multiple spaces with a single space, and converting the strings to lowercase.
 */
public class CaseAndSpaceComparison implements OutputComparator {
    /**
     * Compares the program output and expected output by normalizing both strings.
     * The normalization process includes trimming whitespace, replacing multiple spaces
     * with a single space, and converting the strings to lowercase.
     *
     * @param programOutput   The ExecutionResult object containing the program's output.
     * @param expectedOutput  The ExecutionResult object containing the expected output.
     * @return true if the normalized outputs are identical, false otherwise.
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public boolean compare(ExecutionResult programOutput, ExecutionResult expectedOutput) throws IOException {
        String programNormalized = programOutput.getOutput().trim().replaceAll("\\s+", " ").toLowerCase();
        String expectedNormalized = expectedOutput.getOutput().trim().replaceAll("\\s+", " ").toLowerCase();

        return programNormalized.equals(expectedNormalized);
    }
}