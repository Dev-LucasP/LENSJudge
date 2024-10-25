package lensjudge.main;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import lensjudge.compilation.*;
import lensjudge.check.*;
import lensjudge.execution.ProgramExecutor;


class RunnerBuilderTest {

	private RunnerBuilder builder;

	@BeforeEach
	public void setUp() {
		builder = new RunnerBuilder();
	}

	@Test
	void buildRunnerWithAllFieldsSet() {
		builder.withCompiler(new CCompiler("test.c")).withExecutor(new ProgramExecutor("test"))
				.withComparator(new StrictComparison()).withSourceFile("test.c").withInputFile("test.in")
				.withExpectedOutputFile("test.ans");

		Runner runner = builder.build();
		assertNotNull(runner);
	}

	@Test
	void createRunnerForCFile(@TempDir Path tempDir) throws IOException, InterruptedException {
		Path sourceFile = tempDir.resolve("test.c");
		Files.createFile(sourceFile);

		RunnerBuilder runnerBuilder = RunnerBuilder.createRunner(sourceFile.toString(), "test.in", "test.ans");
		assertNotNull(runnerBuilder);
	}

	@Test
	void createRunnerForUnsupportedFileThrowsException(@TempDir Path tempDir) {
	    Path sourceFile = tempDir.resolve("test.txt");
	    assertThrows(IllegalArgumentException.class, () -> createRunner(sourceFile));
	}

	private void createRunner(Path sourceFile) {
	    try {
	        RunnerBuilder.createRunner(sourceFile.toString(), "test.in", "test.ans");
	    } catch (IOException | InterruptedException e) {
	        throw new RuntimeException("An error occurred: " + e.getMessage(), e);
	    }
	}

	@Test
	void createRunnerForPythonFile(@TempDir Path tempDir) throws IOException, InterruptedException {
		Path sourceFile = tempDir.resolve("Test.py");
		Files.createFile(sourceFile);

		RunnerBuilder runnerBuilder = RunnerBuilder.createRunner(sourceFile.toString(), "test.in", "test.ans");
		assertNotNull(runnerBuilder);
	}
}
