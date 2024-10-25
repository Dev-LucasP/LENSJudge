package lensjudge.main;

import java.io.File;
import java.io.IOException;

import lensjudge.check.*;
import lensjudge.compilation.*;
import lensjudge.execution.*;

public class RunnerBuilder {
	
    protected CompilerStrategy compiler;
    protected ProgramExecutor executor;
    protected StrictComparison comparator;
    protected String sourceFile;
    protected String inputFile;
    protected String expectedOutputFile;

    public RunnerBuilder withCompiler(CompilerStrategy compiler) {
        this.compiler = compiler;
        return this;
    }

    public RunnerBuilder withExecutor(ProgramExecutor executor) {
        this.executor = executor;
        return this;
    }

    public RunnerBuilder withComparator(StrictComparison comparator) {
        this.comparator = comparator;
        return this;
    }

    public RunnerBuilder withSourceFile(String sourceFile) {
        this.sourceFile = new File(sourceFile).getAbsolutePath();
        return this;
    }

    public RunnerBuilder withInputFile(String inputFile) {
        this.inputFile = new File(inputFile).getAbsolutePath();
        return this;
    }

    public RunnerBuilder withExpectedOutputFile(String expectedOutputFile) {
        this.expectedOutputFile = new File(expectedOutputFile).getAbsolutePath();
        return this;
    }

    public Runner build() {
        return new Runner(this);
    }
	

    public static RunnerBuilder createRunner(String sourceFile, String inputFile, String expectedOutputFile) throws IOException, InterruptedException {
    	RunnerBuilder builder = new RunnerBuilder()
            .withSourceFile(sourceFile)
            .withInputFile(inputFile)
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
        } else {
            throw new IllegalArgumentException("Unsupported file extension");
        }

        builder.withComparator(new StrictComparison());

        return builder;
    }
}