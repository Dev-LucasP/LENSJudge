package lensjudge.problem;

import java.util.ArrayList;
import java.util.List;

class ProblemBuilder {
    private List<TestCase> testCases;
    private int timeLimit;
    private int memoryLimit;
    private String validator;

    public ProblemBuilder() {
        this.testCases = new ArrayList<>();
        this.timeLimit = 0;
        this.memoryLimit = 0;
        this.validator = "strict";
    }

    public ProblemBuilder withTestCase(TestCase testCase) {
        this.testCases.add(testCase);
        return this;
    }

    public ProblemBuilder withTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
        return this;
    }

    public ProblemBuilder withMemoryLimit(int memoryLimit) {
        this.memoryLimit = memoryLimit;
        return this;
    }

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