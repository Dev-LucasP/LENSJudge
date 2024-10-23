package lensjudge.check;

import lensjudge.execution.ExecutionResult;

import java.io.IOException;

public class CaseAndSpaceComparison implements OutputComparator {

    @Override
    public boolean compare(ExecutionResult programOutput, ExecutionResult expectedOutput) throws IOException {
        String programNormalized = programOutput.getOutput().trim().replaceAll("\\s+", " ").toLowerCase();
        String expectedNormalized = expectedOutput.getOutput().trim().replaceAll("\\s+", " ").toLowerCase();

        return programNormalized.equals(expectedNormalized);
    }
}