package lensjudge.compilation;

import java.io.IOException;

public class CCompiler extends CompilerStrategy {

    public CCompiler(String sourceFile) {
        super(sourceFile);
    }

    @Override
    public boolean checkFileExtension() {
        return sourceFile.endsWith(".c");
    }

    @Override
    public String deduceBinaryFile(String sourceFile) {
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