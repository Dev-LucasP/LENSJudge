package lensjudge.check;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * The Decorator class extends BufferedReader and provides additional functionality
 * to normalize the case and spaces of each line read from the input.
 */
public class Decorator extends BufferedReader {
    /**
     * Constructs a new Decorator with the specified Reader.
     *
     * @param in the Reader to provide the underlying stream.
     */
    public Decorator(Reader in) {
        super(in);
    }

    /**
     * Converts all characters in the given line to lowercase.
     *
     * @param line the line to be normalized.
     * @return the line with all characters converted to lowercase.
     */
    private String normalizeCase(String line) {
        return line.toLowerCase();
    }

    /**
     * Replaces multiple consecutive whitespace characters in the given line with a single space.
     *
     * @param line the line to be normalized.
     * @return the line with normalized spaces.
     */
    private String normalizeSpaces(String line) {
        return line.replaceAll("\\s+", " ");
    }

    /**
     * Reads a line of text, normalizes its case and spaces, and returns the result.
     *
     * @return a normalized line of text, or null if the end of the stream has been reached.
     * @throws IOException if an I/O error occurs.
     */
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
