package lensjudge.problem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Problem implements Iterable<TestCase> {
    private List<TestCase> testCases;
    private int timeLimit;
    private int memoryLimit;
    private String validator;

    public Problem(int timeLimit, int memoryLimit, String validator) {
        this.testCases = new ArrayList<>();
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
        this.validator = validator;
    }
    
    public Problem() {
        this.testCases = new ArrayList<>();
        this.timeLimit = 0;
        this.memoryLimit = 0;
        this.validator = "strict";
    }

    public void addTestCase(TestCase testCase) {
        testCases.add(testCase);
    }

    @Override
    public Iterator<TestCase> iterator() {
        return testCases.iterator();
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public int getMemoryLimit() {
        return memoryLimit;
    }

    public String getValidator() {
        return validator;
    }
}
