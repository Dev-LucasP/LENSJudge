package lensjudge.process;

import java.io.IOException;

public interface IProcessStrategy{

	Process execute(String[] args) throws IOException;

	void createProcessus();
	
	void stopProcessus() throws InterruptedException;

	String getOutput();

	String getError();
}
