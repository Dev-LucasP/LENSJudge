
package lensjudge.problem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProblemBuilder {

	/**
	 * Builds a Problem instance from the specified directory path. The directory
	 * should contain test case files with ".in" extension for input and
	 * corresponding ".ans" files for expected output.
	 *
	 * @param directoryPath the path to the directory containing test case files
	 * @return a Problem instance containing the test cases
	 * @throws IllegalArgumentException if the provided path is not a directory
	 */
	public static Problem build(String directoryPath) {
		File dir = new File(directoryPath);
		if (!dir.isDirectory()) {
			throw new IllegalArgumentException("Provided path is not a directory.");
		}

		List<TestCase> testCases = new ArrayList<>();

		File[] testFiles = dir.listFiles((d, name) -> name.endsWith(".in"));
		if (testFiles != null) {
			for (File testFile : testFiles) {
				String baseName = testFile.getName().replace(".in", "");
				String inputFilePath = testFile.getAbsolutePath();
				String expectedOutputFilePath = new File(dir, baseName + ".ans").getAbsolutePath();

				TestCase testCase = new TestCase(inputFilePath, expectedOutputFilePath);
				testCases.add(testCase);
			}
		}

		return new Problem(testCases);
	}
}
