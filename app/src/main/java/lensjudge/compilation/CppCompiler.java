package lensjudge.compilation;

import java.io.IOException;

class CppCompiler extends CompilerStrategy {

    public CppCompiler(String sourceFile) {
        super(sourceFile);
    }

    @Override
    protected boolean checkFileExtension() {
        return sourceFile.endsWith(".cpp") || sourceFile.endsWith(".cc") || sourceFile.endsWith(".cxx");
    }

    @Override
    protected String deduceBinaryFile(String sourceFile) {
        return sourceFile.replace(".cpp", ".out").replace(".cc", ".out").replace(".cxx", ".out");
    }

    @Override
    public boolean compile() throws IOException, InterruptedException {
        if (!checkFileExtension()) {
            throw new IllegalArgumentException("Invalid C++ file extension");
        }

        String[] command = { "g++", sourceFile, "-o", deduceBinaryFile(sourceFile) };
        return runCommand(command);
    }
}