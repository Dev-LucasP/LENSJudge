package lensjudge.process;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lensjudge.process.AdapterProcess;
import lensjudge.process.ProcessTimer;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TestProcessTimer {

    private ProcessTimer processTimer;

    @BeforeEach
    void setUp() throws IOException {
        AdapterProcess adapterProcess = new AdapterProcess();
        adapterProcess.createProcessus();
        adapterProcess.execute(new String[] {"sleep", "10"}); // Simulate a long-running process
        processTimer = new ProcessTimer(adapterProcess, 1000); // 1 second timeout
    }

    @Test
    void testTimeOut() throws InterruptedException {
        assertTrue(processTimer.isTimedOut());
    }

    @Test
    void testFalseTimeOut() throws IOException, InterruptedException {
        AdapterProcess adapterProcess = new AdapterProcess();
        adapterProcess.createProcessus();
        adapterProcess.execute(new String[] {"echo", "hello"}); // Simulate a short-running process
        processTimer = new ProcessTimer(adapterProcess, 1000); // 1 second timeout
        assertFalse(processTimer.isTimedOut());
    }
}