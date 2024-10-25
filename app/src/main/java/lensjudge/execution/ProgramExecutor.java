
package lensjudge.execution;

import lensjudge.process.AdapterProcess;

import java.io.File;

import java.io.IOException;

public class ProgramExecutor {
	private String command;
	private AdapterProcess processStrategy;

	public ProgramExecutor(String command) {
		this.command = command;
		this.processStrategy = new AdapterProcess();
	}

	public ExecutionResult execute() throws IOException, InterruptedException {
		processStrategy.createProcessus();
		processStrategy.execute(command.split(" "));
		int exitCode = processStrategy.getProcess().waitFor();
		String output = processStrategy.getOutput();
		String errorOutput = processStrategy.getError();
		return new ExecutionResult(exitCode, output, errorOutput);
	}

	public ExecutionResult executeWithInput(String inputFilePath) throws IOException, InterruptedException {
		processStrategy.createProcessus();
		processStrategy.getProcessBuilder().redirectInput(new File(inputFilePath));
		processStrategy.execute(command.split(" "));
		int exitCode = processStrategy.getProcess().waitFor();
		String output = processStrategy.getOutput();
		String errorOutput = processStrategy.getError();
		return new ExecutionResult(exitCode, output, errorOutput);
	}
}
