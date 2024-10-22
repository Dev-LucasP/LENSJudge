package lensjuge.process;

import java.time.Instant;

public class ProcessTimer {
    private AdapterProcess adapterProcess;
    private long timeOut;
    public ProcessTimer(AdapterProcess adapterProcess, long timeOut) {
        this.adapterProcess = adapterProcess;
        this.timeOut = timeOut;
    }

    public boolean isTimedOut() {
        Instant start = Instant.now();
        Instant end = start.plusMillis(timeOut);
        while (Instant.now().isBefore(end) && adapterProcess.getProcess().isAlive()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (adapterProcess.getProcess().isAlive()) {
            adapterProcess.getProcess().destroy();
            return false;
        }
        return true;
    }
}