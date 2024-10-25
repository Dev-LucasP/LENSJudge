package lensjudge.main;

import lensjudge.check.OutputComparator;
import lensjudge.check.StrictComparison;
import lensjudge.compilation.*;
import lensjudge.execution.ExecutionResult;
import lensjudge.execution.ProgramExecutor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Runner {
    private CompilerStrategy compiler;
    private ProgramExecutor executor;
    private OutputComparator comparator;
    private String sourceFile;
    private String inputFile;
    private String expectedOutputFile;

    public Runner(RunnerBuilder builder) {
        this.compiler = builder.compiler;
        this.executor = builder.executor;
        this.comparator = builder.comparator;
        this.sourceFile = builder.sourceFile;
        this.inputFile = builder.inputFile;
        this.expectedOutputFile = builder.expectedOutputFile;
    }

    public String run() throws IOException, InterruptedException {
        if ((sourceFile.endsWith(".c") && sourceFile.endsWith(".cc") && sourceFile.endsWith(".java")) && !compiler.compile()) {
            return "Compilation Error";
        }

        ExecutionResult programResult = executor.executeWithInput(inputFile);
        if (programResult.getExitCode() != 0) {
            return "Runtime Error";
        }

        ExecutionResult expectedResult = new ExecutionResult(0, Files.readString(Paths.get(expectedOutputFile)), "");
        boolean comparisonResult = comparator.compare(programResult, expectedResult);

        if (!comparisonResult) {
            return "\u001B[31mWRONG ANSWER\u001B[0m";
        }

        return "\u001B[32mCORRECT\u001B[0m";
    }

}

