package lensjudge.problem;

import java.util.ArrayList;
import java.util.List;

/**
 * The ProblemBuilder class is a builder for creating Problem instances.
 * It allows setting test cases, time limit, memory limit, and validator type.
 */
class ProblemBuilder {
    private List<TestCase> testCases;
    private int timeLimit;
    private int memoryLimit;
    private String validator;

    /**
     * Constructs a new ProblemBuilder with default values.
     * The default time limit is 0 milliseconds, the default memory limit is 0 kilobytes, and the default validator is "strict".
     */
    public ProblemBuilder() {
        this.testCases = new ArrayList<>();
        this.timeLimit = 0;
        this.memoryLimit = 0;
        this.validator = "strict";
    }

    /**
     * Adds a test case to the builder.
     *
     * @param testCase the test case to be added.
     * @return the current instance of ProblemBuilder.
     */
    public ProblemBuilder withTestCase(TestCase testCase) {
        this.testCases.add(testCase);
        return this;
    }

    /**
     * Sets the time limit for the problem.
     *
     * @param timeLimit the time limit in milliseconds.
     * @return the current instance of ProblemBuilder.
     */
    public ProblemBuilder withTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
        return this;
    }

    /**
     * Sets the memory limit for the problem.
     *
     * @param memoryLimit the memory limit in kilobytes.
     * @return the current instance of ProblemBuilder.
     */
    public ProblemBuilder withMemoryLimit(int memoryLimit) {
        this.memoryLimit = memoryLimit;
        return this;
    }

    /**
     * Builds and returns a Problem instance with the specified properties.
     *
     * @return a new Problem instance.
     */
    public ProblemBuilder withValidator(String validator) {
        this.validator = validator;
        return this;
    }

    public Problem build() {
        Problem problem = new Problem(timeLimit, memoryLimit, validator);
        for (TestCase testCase : testCases) {
            problem.addTestCase(testCase);
        }
        return problem;
    }
}