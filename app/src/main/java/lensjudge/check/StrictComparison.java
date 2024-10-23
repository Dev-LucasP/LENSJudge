package lensjudge.check;

import java.io.IOException;

import lensjudge.execution.*;

public class StrictComparison implements OutputComparator {

	@Override
	public boolean compare(ExecutionResult programOutput, ExecutionResult expectedOutput) throws IOException {
	    return programOutput.getOutput().equals(expectedOutput.getOutput()) &&
	    		programOutput.getErrorOutput().equals(expectedOutput.getErrorOutput());
	}
}
