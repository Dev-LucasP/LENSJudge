package lensjuge.process;

public interface IProcessStrategy{
	
	public String execute(String[] args);
	
	public Process createProcessus();
	
	public void stopProcessus() throws InterruptedException;
	
	public String getInput();
	
	public String getOutput();

	public String getError();
}
