package lensjudge.compilation;

import java.io.IOException;

import lensjudge.process.*;

class CppCompiler extends CompilerStrategy {
	
	public CppCompiler(String sourceFile) {
		super(sourceFile);
	}

	@Override
    protected boolean checkFileExtension() {
        return sourceFile.endsWith(".cpp") || sourceFile.endsWith(".cc") || sourceFile.endsWith(".cxx");
    }
    
	@Override
    protected String deduceBinaryFile() {
        return sourceFile.replace(".cpp", ".out").replace(".cc", ".out").replace(".cxx", ".out");
    }
    
	@Override
	public void compile() throws IOException {
    	AdapterProcess adapterProcess = new AdapterProcess();
        adapterProcess.createProcessus();
        adapterProcess.execute(new String[]{"g++", "-x", "c++", "-Wall", "-02", "-static", "-pipe", "-o", "exe", sourceFile});
    }
}