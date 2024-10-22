package lensjuge.process;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestAdapterProcess {

    @Test
    public void testExecute() {
        AdapterProcess adapterProcess = new AdapterProcess();
        adapterProcess.createProcessus();
        try {
            adapterProcess.execute(new String[]{"ls"});
            adapterProcess.getOutput();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

