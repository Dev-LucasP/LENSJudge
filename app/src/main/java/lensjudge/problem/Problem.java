package lensjudge.problem;

import java.util.List;

public class Problem implements Iterable<TestCase> {
    private List<TestCase> testCases;

    public Problem(List<TestCase> testCases) {
        this.testCases = testCases;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    @Override
    public java.util.Iterator<TestCase> iterator() {
        return testCases.iterator();
    }
}
