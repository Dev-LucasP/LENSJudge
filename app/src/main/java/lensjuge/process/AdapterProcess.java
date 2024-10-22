package lensjuge.process;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class AdapterProcess implements IProcessStrategy {
	private ProcessBuilder processBuilder;
	private Process process;
	private String output;
	private String error;
	public AdapterProcess() {
		this.processBuilder = null;
		this.process = null;
	}

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

	@Override
	public void createProcessus() {
		this.processBuilder = new ProcessBuilder();
	}

	@Override
	public void stopProcessus() {
		try {
			process.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getInput() {
/*		try {
			InputStream inputStream = process.getInputStream();
			return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}*/
		return null;
	}

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

	public Process getProcess() {
		return process;
	}

	public ProcessBuilder getProcessBuilder() {
		return processBuilder;
	}
}
