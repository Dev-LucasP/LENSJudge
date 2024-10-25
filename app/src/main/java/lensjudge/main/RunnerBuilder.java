
package lensjudge.main;

import java.io.File;
import java.io.IOException;

import lensjudge.check.*;
import lensjudge.compilation.*;
import lensjudge.execution.*;

/**
 * Builder class for creating instances of {@link Runner}.
 */
public class RunnerBuilder {

	protected CompilerStrategy compiler;
	protected ProgramExecutor executor;
	protected StrictComparison comparator;
	protected String sourceFile;
	protected String inputFile;
	protected String expectedOutputFile;

	/**
	 * Sets the compiler strategy for the runner.
	 *
	 * @param compiler the compiler strategy
	 * @return the current instance of {@link RunnerBuilder}
	 */
	public RunnerBuilder withCompiler(CompilerStrategy compiler) {
		this.compiler = compiler;
		return this;
	}

	/**
	 * Sets the program executor for the runner.
	 *
	 * @param executor the program executor
	 * @return the current instance of {@link RunnerBuilder}
	 */
	public RunnerBuilder withExecutor(ProgramExecutor executor) {
		this.executor = executor;
		return this;
	}

	/**
	 * Sets the comparator for the runner.
	 *
	 * @param comparator the strict comparison comparator
	 * @return the current instance of {@link RunnerBuilder}
	 */
	public RunnerBuilder withComparator(StrictComparison comparator) {
		this.comparator = comparator;
		return this;
	}

	/**
	 * Sets the source file for the runner.
	 *
	 * @param sourceFile the path to the source file
	 * @return the current instance of {@link RunnerBuilder}
	 */
	public RunnerBuilder withSourceFile(String sourceFile) {
		this.sourceFile = new File(sourceFile).getAbsolutePath();
		return this;
	}

	/**
	 * Sets the input file for the runner.
	 *
	 * @param inputFile the path to the input file
	 * @return the current instance of {@link RunnerBuilder}
	 */
	public RunnerBuilder withInputFile(String inputFile) {
		this.inputFile = new File(inputFile).getAbsolutePath();
		return this;
	}

	/**
	 * Sets the expected output file for the runner.
	 *
	 * @param expectedOutputFile the path to the expected output file
	 * @return the current instance of {@link RunnerBuilder}
	 */
	public RunnerBuilder withExpectedOutputFile(String expectedOutputFile) {
		this.expectedOutputFile = new File(expectedOutputFile).getAbsolutePath();
		return this;
	}

	/**
	 * Builds and returns a {@link Runner} instance.
	 *
	 * @return a new instance of {@link Runner}
	 */
	public Runner build() {
		return new Runner(this);
	}

	/**
	 * Creates a {@link RunnerBuilder} and configures it based on the source file
	 * type.
	 *
	 * @param sourceFile         the path to the source file
	 * @param inputFile          the path to the input file
	 * @param expectedOutputFile the path to the expected output file
	 * @return a configured instance of {@link RunnerBuilder}
	 * @throws IOException          if an I/O error occurs
	 * @throws InterruptedException if the compilation or execution is interrupted
	 */
	public static RunnerBuilder createRunner(String sourceFile, String inputFile, String expectedOutputFile)
			throws IOException, InterruptedException {
		RunnerBuilder builder = new RunnerBuilder().withSourceFile(sourceFile).withInputFile(inputFile)
				.withExpectedOutputFile(expectedOutputFile);

		if (sourceFile.endsWith(".c")) {
			CCompiler c = new CCompiler(sourceFile);
			c.compile();
			String binaryFile = c.deduceBinaryFile(sourceFile);
			builder.withCompiler(new CCompiler(sourceFile));
			builder.withExecutor(new ProgramExecutor(binaryFile));
		} else if (sourceFile.endsWith(".cc")) {
			CppCompiler cpp = new CppCompiler(sourceFile);
			cpp.compile();
			String binaryFile = cpp.deduceBinaryFile(sourceFile);
			builder.withCompiler(new CppCompiler(sourceFile));
			builder.withExecutor(new ProgramExecutor(binaryFile));
		} else if (sourceFile.endsWith(".py")) {
			builder.withExecutor(new ProgramExecutor("python3 " + sourceFile));
		} else if (sourceFile.endsWith(".java")) {
			JavaCompiler java = new JavaCompiler(sourceFile);
			java.compile();
			String binaryFile = java.deduceBinaryFile(sourceFile);
			builder.withCompiler(new JavaCompiler(sourceFile));
			builder.withExecutor(new ProgramExecutor(binaryFile));
		} else {
			throw new IllegalArgumentException("Unsupported file extension");
		}

		builder.withComparator(new StrictComparison());

		return builder;
	}
}
