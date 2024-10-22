package lensjuge.process;

public interface IProcessStrategy{
	
	public String execute();
	
	public Process createProcessus();
	
	public void stopProcessus();
	
	public String getInput();
	
	public String getOutput();

	public String getError();
}
