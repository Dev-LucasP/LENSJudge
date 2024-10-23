package lensjudge.check;

import lensjudge.execution.ExecutionResult;

import java.io.IOException;
import java.util.List;

public class MultipleSolutionsComparison implements OutputComparator {
    private List<String> validSolutions;

    public MultipleSolutionsComparison(List<String> validSolutions) {
        this.validSolutions = validSolutions;
    }

    @Override
    public boolean compare(ExecutionResult programOutput, ExecutionResult expectedOutput) throws IOException {
        String programSolution = programOutput.getOutput().trim();
        return validSolutions.contains(programSolution);
    }
}
