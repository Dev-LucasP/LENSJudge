package lensjuge.process;

import java.io.IOException;

public interface IProcessStrategy{

	public Process execute(String[] args) throws IOException;

	public void createProcessus();
	
	public void stopProcessus() throws InterruptedException;
	
	public String getInput();
	
	public String getOutput();

	public String getError();
}
