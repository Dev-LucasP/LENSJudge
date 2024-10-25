
package lensjudge.execution;

import lensjudge.process.AdapterProcess;
import lensjudge.process.ProcessTimer;

import java.io.File;
import java.io.IOException;

/**
 * Class responsible for executing a program with or without input redirection.
 */
public class ProgramExecutor {
	private String command;
	private AdapterProcess processStrategy;

	/**
	 * Constructs a ProgramExecutor with the specified command.
	 *
	 * @param command the command to execute
	 */
	public ProgramExecutor(String command) {
		this.command = command;
		this.processStrategy = new AdapterProcess();
	}

	/**
	 * Executes the command without input redirection.
	 *
	 * @return the result of the execution, including exit code, output, and error
	 *         output
	 * @throws IOException          if an I/O error occurs
	 * @throws InterruptedException if the execution is interrupted
	 */
	public ExecutionResult execute() throws IOException, InterruptedException {
		processStrategy.createProcessus();
		processStrategy.execute(command.split(" "));
		int exitCode = processStrategy.getProcess().waitFor();
		String output = processStrategy.getOutput();
		String errorOutput = processStrategy.getError();
		ProcessTimer processTimer = new ProcessTimer(processStrategy, 10);
		if (processTimer.isTimedOut()) {
			return new ExecutionResult(-1, "", "Process as time out");
		} else {
			return new ExecutionResult(exitCode, output, errorOutput);
		}
	}

	/**
	 * Executes the command with input redirection from the specified file.
	 *
	 * @param inputFilePath the path to the input file
	 * @return the result of the execution, including exit code, output, and error
	 *         output
	 * @throws IOException          if an I/O error occurs
	 * @throws InterruptedException if the execution is interrupted
	 */
	public ExecutionResult executeWithInput(String inputFilePath) throws IOException, InterruptedException {
		processStrategy.createProcessus();
		processStrategy.getProcessBuilder().redirectInput(new File(inputFilePath));
		processStrategy.execute(command.split(" "));
		int exitCode = processStrategy.getProcess().waitFor();
		String output = processStrategy.getOutput();
		String errorOutput = processStrategy.getError();
		ProcessTimer processTimer = new ProcessTimer(processStrategy, 10);
		if (processTimer.isTimedOut()) {
			return new ExecutionResult(-1, "", "Process as time out");
		} else {
			return new ExecutionResult(exitCode, output, errorOutput);
		}
	}
}
