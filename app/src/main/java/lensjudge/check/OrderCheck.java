package lensjudge.check;

import java.util.ArrayList;

public class OrderCheck {
    private ArrayList<String> result;
    private ArrayList<String> input;

    public OrderCheck(ArrayList<String> result, ArrayList<String> input) {
        this.result = result;
        this.input = input;
    }

    public void input() {
        // TODO : Implement this method
    }

    public boolean check() {
        if (result.size() != input.size()) {
            return false;
        }

        for (String item : input) {
            if (!result.contains(item)) {
                return false;
            }
        }
        return true;
    }
}
