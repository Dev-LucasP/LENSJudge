package lensjudge.compilation;

import java.io.IOException;

class CCompiler extends CompilerStrategy {

    public CCompiler(String sourceFile) {
        super(sourceFile);
    }

    @Override
    protected boolean checkFileExtension() {
        return sourceFile.endsWith(".c");
    }

    @Override
    protected String deduceBinaryFile(String sourceFile) {
        return sourceFile.replace(".c", ".out");
    }

    @Override
    public boolean compile() throws IOException, InterruptedException {
        if (!checkFileExtension()) {
            throw new IllegalArgumentException("Invalid C file extension");
        }

        String[] command = { "gcc", sourceFile, "-o", deduceBinaryFile(sourceFile) };
        return runCommand(command);
    }
}