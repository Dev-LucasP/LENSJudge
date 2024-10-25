package lensjudge.problem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;

class ProblemTest {


	@Test
	void buildProblemFromValidDirectory() throws Exception {
	    Path tempDir = Files.createTempDirectory("testDir");
	    Files.createFile(tempDir.resolve("test1.in"));
	    Files.createFile(tempDir.resolve("test1.ans"));
	    Files.createFile(tempDir.resolve("test2.in"));
	    Files.createFile(tempDir.resolve("test2.ans"));
	
	    Problem problem = ProblemBuilder.build(tempDir.toString());
	    assertEquals(2, problem.getTestCases().size());
	}
	
	@Test
	void buildProblemFromEmptyDirectory() throws Exception {
	    Path tempDir = Files.createTempDirectory("testDir");
	
	    Problem problem = ProblemBuilder.build(tempDir.toString());
	    assertEquals(0, problem.getTestCases().size());
	}
	
	@Test
	void buildProblemFromInvalidDirectoryPath() {
	    assertThrows(IllegalArgumentException.class, () -> ProblemBuilder.build("invalid/path"));
	}

}
