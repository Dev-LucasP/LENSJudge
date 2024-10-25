
package lensjudge.problem;

import java.util.List;

/**
 * Represents a problem consisting of multiple test cases. Implements the
 * Iterable interface to allow iteration over the test cases.
 */
public class Problem implements Iterable<TestCase> {
	private List<TestCase> testCases;

	/**
	 * Constructs a Problem with the specified list of test cases.
	 *
	 * @param testCases the list of test cases
	 */
	public Problem(List<TestCase> testCases) {
		this.testCases = testCases;
	}

	/**
	 * Returns the list of test cases.
	 *
	 * @return the list of test cases
	 */
	public List<TestCase> getTestCases() {
		return testCases;
	}

	/**
	 * Returns an iterator over the test cases.
	 *
	 * @return an iterator over the test cases
	 */
	@Override
	public java.util.Iterator<TestCase> iterator() {
		return testCases.iterator();
	}
}
