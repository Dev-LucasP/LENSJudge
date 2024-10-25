package lensjudge.check;

import java.io.IOException;

import lensjudge.execution.*;

/**
 * The StrictComparison class implements the OutputComparator interface
 * and provides a method to compare the program output with the expected output.
 * The comparison checks if both the standard output and error output are exactly equal.
 */
public class StrictComparison implements OutputComparator {

	/**
	 * Compares the program output with the expected output.
	 * The comparison checks if both the standard output and error output are exactly equal.
	 *
	 * @param programOutput   The ExecutionResult object containing the program's output.
	 * @param expectedOutput  The ExecutionResult object containing the expected output.
	 * @return true if both the standard output and error output are exactly equal, false otherwise.
	 * @throws IOException if an I/O error occurs.
	 */
	@Override
	public boolean compare(ExecutionResult programOutput, ExecutionResult expectedOutput) throws IOException {
	    return programOutput.getOutput().equals(expectedOutput.getOutput()) &&
	    		programOutput.getErrorOutput().equals(expectedOutput.getErrorOutput());
	}
}
