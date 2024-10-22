package lensjuge.process;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TestAdapterProcess {

    @Test
    void testExecute() {
        AdapterProcess adapterProcess = new AdapterProcess();
        adapterProcess.createProcessus();
        try {
            adapterProcess.execute(new String[]{"ls", "-la", "/"});
            String actualOutput = adapterProcess.getOutput();

            assertTrue(actualOutput.contains("total ")); // Check if the output contains "total xxxx"
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    void testCreateProcessus() {
        AdapterProcess adapterProcess = new AdapterProcess();
        adapterProcess.createProcessus();
        assertNotNull(adapterProcess);
    }

    @Test
    void testStopProcessus() {
        AdapterProcess adapterProcess = new AdapterProcess();
        adapterProcess.createProcessus();
        try {
            adapterProcess.execute(new String[]{"ls", "-la", "/"});
            adapterProcess.stopProcessus();
            assertNull(adapterProcess.getOutput());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetError() {
        AdapterProcess adapterProcess = new AdapterProcess();
        adapterProcess.createProcessus();
        try {
            adapterProcess.execute(new String[]{"ls", "-la", "/doesnotexist"});
            String actualError = adapterProcess.getError();
            assertTrue(actualError.contains("" +
                    "ls: impossible d'accéder à '/doesnotexist': Aucun fichier ou dossier de ce nom\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

