package lensjudge.problem;

public class TestCase {
    private String inputFile;
    private String expectedOutputFile;

    public TestCase(String inputFile, String expectedOutputFile) {
        this.inputFile = inputFile;
        this.expectedOutputFile = expectedOutputFile;
    }

    public String getInputFile() {
        return inputFile;
    }

    public String getExpectedOutputFile() {
        return expectedOutputFile;
    }
}
