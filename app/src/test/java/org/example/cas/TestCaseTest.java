import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestCaseTest {
    @Test
    public void testGettersAndSetters() {
        TestCase testCase = new TestCase("input.in", "output.ans");

        assertEquals("input.in", testCase.getInputFilePath());
        assertEquals("output.ans", testCase.getOutputFilePath());

        testCase.setInputFilePath("newInput.in");
        testCase.setOutputFilePath("newOutput.ans");

        assertEquals("newInput.in", testCase.getInputFilePath());
        assertEquals("newOutput.ans", testCase.getOutputFilePath());
    }
}