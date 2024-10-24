package lensjudge.main;

import lensjudge.check.OutputComparator;
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

    // Constructor privé pour utiliser avec un Builder
    private Runner(Builder builder) {
        this.compiler = builder.compiler;
        this.executor = builder.executor;
        this.comparator = builder.comparator;
        this.sourceFile = builder.sourceFile;
        this.inputFile = builder.inputFile;
        this.expectedOutputFile = builder.expectedOutputFile;
    }

    public String run() throws IOException, InterruptedException {
        // Étape 1 : Compilation
        if ((sourceFile.endsWith(".c") && sourceFile.endsWith(".cc")) && !compiler.compile()) {
            return "Compilation Error";
        }

        // Étape 2 : Exécution du programme
        ExecutionResult programResult = executor.executeWithInput(inputFile);
        if (programResult.getExitCode() != 0) {
            return "Runtime Error";
        }

        // Étape 3 : Comparaison de la sortie
        ExecutionResult expectedResult = new ExecutionResult(0, Files.readString(Paths.get(expectedOutputFile)), "");
        boolean comparisonResult = comparator.compare(programResult, expectedResult);

        if (!comparisonResult) {
            return "Wrong Answer";
        }

        return "CORRECT";
    }

    // Builder pour construire un Runner
    public static class Builder {
        private CompilerStrategy compiler;
        private ProgramExecutor executor;
        private OutputComparator comparator;
        private String sourceFile;
        private String inputFile;
        private String expectedOutputFile;

        public Builder withCompiler(CompilerStrategy compiler) {
            this.compiler = compiler;
            return this;
        }

        public Builder withExecutor(ProgramExecutor executor) {
            this.executor = executor;
            return this;
        }

        public Builder withComparator(OutputComparator comparator) {
            this.comparator = comparator;
            return this;
        }

        public Builder withSourceFile(String sourceFile) {
            this.sourceFile = new File(sourceFile).getAbsolutePath(); // Chemin absolu
            return this;
        }

        public Builder withInputFile(String inputFile) {
            this.inputFile = new File(inputFile).getAbsolutePath(); // Chemin absolu
            return this;
        }

        public Builder withExpectedOutputFile(String expectedOutputFile) {
            this.expectedOutputFile = new File(expectedOutputFile).getAbsolutePath(); // Chemin absolu
            return this;
        }

        public Runner build() {
            return new Runner(this);
        }
    }

}

