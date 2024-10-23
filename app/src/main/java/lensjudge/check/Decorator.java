package lensjudge.check;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class Decorator extends BufferedReader {

    public Decorator(Reader in) {
        super(in);
    }

    private String normalizeCase(String line) {
        return line.toLowerCase();
    }

    private String normalizeSpaces(String line) {
        return line.replaceAll("\\s+", " ");
    }

    @Override
    public String readLine() throws IOException {
        String line = super.readLine();
        if (line != null) {
            line = normalizeCase(line);
            line = normalizeSpaces(line);
        }
        return line;
    }
}
