package lensjudge.check;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class OrderCheckTest {

    @Test
    void checkShouldReturnTrueWhenResultAndInputAreIdentical() {
        ArrayList<String> result = new ArrayList<>();
        result.add("item1");
        result.add("item2");

        ArrayList<String> input = new ArrayList<>();
        input.add("item1");
        input.add("item2");

        OrderCheck orderCheck = new OrderCheck(result, input);
        assertTrue(orderCheck.check());
    }

    @Test
    void checkShouldReturnFalseWhenResultAndInputSizesDiffer() {
        ArrayList<String> result = new ArrayList<>();
        result.add("item1");

        ArrayList<String> input = new ArrayList<>();
        input.add("item1");
        input.add("item2");

        OrderCheck orderCheck = new OrderCheck(result, input);
        assertFalse(orderCheck.check());
    }

    @Test
    void checkShouldReturnFalseWhenResultDoesNotContainAllInputItems() {
        ArrayList<String> result = new ArrayList<>();
        result.add("item1");
        result.add("item3");

        ArrayList<String> input = new ArrayList<>();
        input.add("item1");
        input.add("item2");

        OrderCheck orderCheck = new OrderCheck(result, input);
        assertFalse(orderCheck.check());
    }

    @Test
    void checkShouldReturnTrueWhenResultContainsAllInputItemsInDifferentOrder() {
        ArrayList<String> result = new ArrayList<>();
        result.add("item2");
        result.add("item1");

        ArrayList<String> input = new ArrayList<>();
        input.add("item1");
        input.add("item2");

        OrderCheck orderCheck = new OrderCheck(result, input);
        assertTrue(orderCheck.check());
    }

    @Test
    void checkShouldReturnTrueWhenBothResultAndInputAreEmpty() {
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> input = new ArrayList<>();

        OrderCheck orderCheck = new OrderCheck(result, input);
        assertTrue(orderCheck.check());
    }
}