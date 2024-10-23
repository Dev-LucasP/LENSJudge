package lensjudge.process;

import java.io.IOException;

/**
 * The IProcessStrategy interface defines the contract for process management strategies.
 * It includes methods to execute, create, stop a process, and retrieve its output and error streams.
 */
public interface IProcessStrategy{

	/**
	 * Executes the process with the specified arguments.
	 *
	 * @param args the command and its arguments to be executed.
	 * @return the Process object representing the running process.
	 * @throws IOException if an I/O error occurs during process execution.
	 */
	Process execute(String[] args) throws IOException;

	/**
	 * Initializes the process.
	 */
	void createProcessus();

	/**
	 * Stops the running process.
	 *
	 * @throws InterruptedException if the current thread is interrupted while waiting for the process to stop.
	 */
	void stopProcessus() throws InterruptedException;

	/**
	 * Retrieves the standard output of the process.
	 *
	 * @return the standard output of the process as a String.
	 */
	String getOutput();

	/**
	 * Retrieves the error output of the process.
	 *
	 * @return the error output of the process as a String.
	 */
	String getError();
}
