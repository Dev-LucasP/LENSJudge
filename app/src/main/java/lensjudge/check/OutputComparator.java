package lensjudge.check;

import java.io.IOException;

import lensjudge.execution.ExecutionResult;

public interface OutputComparator {
	boolean compare(ExecutionResult programOutput, ExecutionResult expectedOutput) throws IOException;
}
