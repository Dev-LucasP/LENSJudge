package lensjudge.main;

import java.io.IOException;

import lensjudge.check.*;
import lensjudge.compilation.*;
import lensjudge.execution.*;

public class RunnerBuilder {

    public static Runner.Builder createRunner(String sourceFile, String inputFile, String expectedOutputFile) throws IOException, InterruptedException {
        Runner.Builder builder = new Runner.Builder()
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