package lensjudge.problem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The Problem class represents a problem with a set of test cases, time limit, memory limit, and a validator.
 * It implements the Iterable interface to allow iteration over its test cases.
 */
class Problem implements Iterable<TestCase> {
    private List<TestCase> testCases;
    private int timeLimit;
    private int memoryLimit;
    private String validator;

    /**
     * Constructs a new Problem with the specified time limit, memory limit, and validator.
     *
     * @param timeLimit the time limit for the problem in milliseconds.
     * @param memoryLimit the memory limit for the problem in kilobytes.
     * @param validator the validator type for the problem.
     */
    public Problem(int timeLimit, int memoryLimit, String validator) {
        this.testCases = new ArrayList<>();
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
        this.validator = validator;
    }

    /**
     * Constructs a new Problem with default values.
     * The default time limit is 0 milliseconds, the default memory limit is 0 kilobytes, and the default validator is "strict".
     */
    public Problem() {
        this.testCases = new ArrayList<>();
        this.timeLimit = 0;
        this.memoryLimit = 0;
        this.validator = "strict";
    }

    /**
     * Adds a test case to the problem.
     *
     * @param testCase the test case to be added.
     */
    public void addTestCase(TestCase testCase) {
        testCases.add(testCase);
    }

    /**
     * Returns an iterator over the test cases in this problem.
     *
     * @return an Iterator over the test cases.
     */
    @Override
    public Iterator<TestCase> iterator() {
        return testCases.iterator();
    }

    /**
     * Returns the time limit for the problem.
     *
     * @return the time limit in milliseconds.
     */
    public int getTimeLimit() {
        return timeLimit;
    }

    /**
     * Returns the memory limit for the problem.
     *
     * @return the memory limit in kilobytes.
     */
    public int getMemoryLimit() {
        return memoryLimit;
    }

    /**
     * Returns the validator type for the problem.
     *
     * @return the validator type.
     */
    public String getValidator() {
        return validator;
    }
}
