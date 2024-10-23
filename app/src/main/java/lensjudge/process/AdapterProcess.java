package lensjudge.process;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * The AdapterProcess class implements the IProcessStrategy interface and provides methods
 * to create, execute, stop a process, and retrieve its output and error streams.
 */
public class AdapterProcess implements IProcessStrategy {
	private ProcessBuilder processBuilder;
	private Process process;
	private String output;
	private String error;

	/**
	 * Constructs a new AdapterProcess with uninitialized processBuilder and process.
	 */
	public AdapterProcess() {
		this.processBuilder = null;
		this.process = null;
		this.output = null;
	}

	/**
	 * Executes the process with the specified arguments.
	 *
	 * @param args the command and its arguments to be executed.
	 * @return the Process object representing the running process.
	 * @throws IOException if an I/O error occurs during process execution.
	 */
	@Override
	public Process execute(String[] args) throws IOException {
		try {
			if (processBuilder == null) {
				throw new IOException("ProcessBuilder is not initialized");
			}
			this.process = processBuilder.command(args).start();
			return this.process;

		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Initializes the processBuilder.
	 */
	@Override
	public void createProcessus() {
		this.processBuilder = new ProcessBuilder();
	}

	/**
	 * Stops the running process.
	 */
	@Override
	public void stopProcessus() {
		try {
			process.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retrieves the standard output of the process.
	 *
	 * @return the standard output of the process as a String.
	 */
	@Override
	public String getOutput() {
		try {
			InputStream inputStream = process.getInputStream();
			this.output = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
			return this.output;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Retrieves the error output of the process.
	 *
	 * @return the error output of the process as a String.
	 */
	@Override
	public String getError() {
		try {
			InputStream inputStream = process.getErrorStream();
			this.error = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
			return this.error;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Returns the Process object representing the running process.
	 *
	 * @return the Process object.
	 */
	public Process getProcess() {
		return process;
	}

	/**
	 * Returns the ProcessBuilder object used to create the process.
	 *
	 * @return the ProcessBuilder object.
	 */
	public ProcessBuilder getProcessBuilder() {
		return processBuilder;
	}
}
