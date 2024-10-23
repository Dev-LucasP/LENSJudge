package lensjudge.problem;

/**
 * Represents a test case with an input file and an output file.
 */
public class TestCase {
    private String inputFilePath;
    private String outputFilePath;

    /**
     * Constructs a new TestCase with the specified input and output file paths.
     *
     * @param inputFilePath the path to the input file
     * @param outputFilePath the path to the output file
     */
    public TestCase(String inputFilePath, String outputFilePath) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
    }

    /**
     * Returns the path to the input file.
     *
     * @return the input file path
     */
    public String getInputFilePath() {
        return inputFilePath;
    }

    /**
     * Sets the path to the input file.
     *
     * @param inputFilePath the new input file path
     */
    public void setInputFilePath(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    /**
     * Returns the path to the output file.
     *
     * @return the output file path
     */
    public String getOutputFilePath() {
        return outputFilePath;
    }

    /**
     * Sets the path to the output file.
     *
     * @param outputFilePath the new output file path
     */
    public void setOutputFilePath(String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }
}