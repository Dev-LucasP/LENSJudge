/*
package lensjudge.problem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

class ProblemTest {

    private TestCase testCase1;
    private TestCase testCase2;

    @BeforeEach
    void setUp() {
        testCase1 = new TestCase("input1.in", "output1.ans");
        testCase2 = new TestCase("input2.in", "output2.ans");
    }

    @Test
    void testTestCaseCreation() {
        assertEquals("input1.in", testCase1.getInputFilePath());
        assertEquals("output1.ans", testCase1.getOutputFilePath());
    }

    @Test
    void testAddTestCaseToProblem() {
        Problem problem = new Problem(2, 128, "strict");

        problem.addTestCase(testCase1);
        problem.addTestCase(testCase2);

        Iterator<TestCase> iterator = problem.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(testCase1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(testCase2, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    void testProblemAttributes() {
        Problem problem = new Problem(3, 256, "tolerance");

        assertEquals(3, problem.getTimeLimit());
        assertEquals(256, problem.getMemoryLimit());
        assertEquals("tolerance", problem.getValidator());
    }

    @Test
    void testProblemBuilder() {
        Problem problem = new ProblemBuilder()
                .withTestCase(testCase1)
                .withTestCase(testCase2)
                .withTimeLimit(5)
                .withMemoryLimit(512)
                .withValidator("strict")
                .build();

        assertEquals(5, problem.getTimeLimit());
        assertEquals(512, problem.getMemoryLimit());
        assertEquals("strict", problem.getValidator());

        Iterator<TestCase> iterator = problem.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(testCase1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(testCase2, iterator.next());
        assertFalse(iterator.hasNext());
    }
}
*/
