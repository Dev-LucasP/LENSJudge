package lensjudge.problem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestCaseTest {
	

	@Test
	void getInputFileReturnsCorrectValue() {
	    TestCase testCase = new TestCase("input.in", "output.ans");
	    assertEquals("input.in", testCase.getInputFile());
	}
	
	@Test
	void getExpectedOutputFileReturnsCorrectValue() {
	    TestCase testCase = new TestCase("input.in", "output.ans");
	    assertEquals("output.ans", testCase.getExpectedOutputFile());
	}  
}
