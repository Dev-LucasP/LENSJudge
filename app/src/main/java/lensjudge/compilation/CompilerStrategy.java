package lensjudge.compilation;

public abstract class CompilerStrategy {
	
	protected abstract boolean checkFileExtension();
	
	protected abstract String deduceBinaryFile();
	
	protected abstract void compile();

}
