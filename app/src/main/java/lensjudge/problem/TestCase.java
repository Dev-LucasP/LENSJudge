
package lensjudge.problem;

/**
 * Represents a test case with an input file and an expected output file.
 */
public class TestCase {
	private String inputFile;
	private String expectedOutputFile;

	/**
	 * Constructs a TestCase with the specified input and expected output files.
	 *
	 * @param inputFile          the path to the input file
	 * @param expectedOutputFile the path to the expected output file
	 */
	public TestCase(String inputFile, String expectedOutputFile) {
		this.inputFile = inputFile;
		this.expectedOutputFile = expectedOutputFile;
	}

	/**
	 * Returns the path to the input file.
	 *
	 * @return the input file path
	 */
	public String getInputFile() {
		return inputFile;
	}

	/**
	 * Returns the path to the expected output file.
	 *
	 * @return the expected output file path
	 */
	public String getExpectedOutputFile() {
		return expectedOutputFile;
	}
}
