package lensjuge.process;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestAdapterProcess {

    @Test
    public void testExecute() {
        AdapterProcess adapterProcess = new AdapterProcess();
        adapterProcess.createProcessus();
        try {
            adapterProcess.execute(new String[]{"ls", "-la", "/"});
            String actualOutput = adapterProcess.getOutput();

            assertTrue(actualOutput.contains("total 68"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testCreateProcessus() {
        AdapterProcess adapterProcess = new AdapterProcess();
        adapterProcess.createProcessus();
        assertNotNull(adapterProcess);
    }

    @Test
    public void testStopProcessus() {
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
    public void testGetError() {
        AdapterProcess adapterProcess = new AdapterProcess();
        adapterProcess.createProcessus();
        try {
            adapterProcess.execute(new String[]{"ls", "-la", "/doesnotexist"});
            String actualError = adapterProcess.getError();
            assertTrue(actualError.contains("ls: impossible d'accéder à '/doesnotexist': Aucun fichier ou dossier de ce nom\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

