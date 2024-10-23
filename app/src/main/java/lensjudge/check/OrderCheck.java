package lensjudge.check;

import lensjudge.execution.ExecutionResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderCheck implements OutputComparator{

    @Override
    public boolean compare(ExecutionResult programOutput, ExecutionResult expectedOutput) throws IOException {
        ArrayList<String> programOutputLines = new ArrayList<>(List.of(programOutput.getOutput().split(" ")));
        ArrayList<String> expectedOutputLines = new ArrayList<>(List.of(expectedOutput.getOutput().split(" ")));

        if (programOutputLines.size() != expectedOutputLines.size()) {
            return false;
        }

        for (String line : expectedOutputLines) {
            if (!programOutputLines.contains(line)) {
                return false;
            }
        }
        return true;
    }
}
