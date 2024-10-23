package lensjudge.check;

import java.io.IOException;

import lensjudge.execution.ExecutionResult;
/**
 * The OutputComparator interface defines a method for comparing the program output
 * with the expected output.
 */
public interface OutputComparator {
	/**
	 * Compares the program output with the expected output.
	 *
	 * @param programOutput   The ExecutionResult object containing the program's output.
	 * @param expectedOutput  The ExecutionResult object containing the expected output.
	 * @return true if the program output matches the expected output, false otherwise.
	 * @throws IOException if an I/O error occurs.
	 */
	boolean compare(ExecutionResult programOutput, ExecutionResult expectedOutput) throws IOException;
}
