package lensjudge.check;

import org.junit.jupiter.api.Test;
import java.io.StringReader;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DecoratorTest {

    @Test
    void testReadLine() throws IOException {
        String input = "Hello   World\nThis is a TEST";
        String expectedOutput1 = "hello world";
        String expectedOutput2 = "this is a test";

        try (Decorator decorator = new Decorator(new StringReader(input))) {
            assertEquals(expectedOutput1, decorator.readLine());
            assertEquals(expectedOutput2, decorator.readLine());
        }
    }
}