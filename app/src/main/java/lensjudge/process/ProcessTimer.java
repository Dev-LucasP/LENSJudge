package lensjudge.process;

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
        while (Instant.now().isBefore(end)) {
            if (!adapterProcess.getProcess().isAlive()) {
                return false;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return false;
            }
        }
        if (adapterProcess.getProcess().isAlive()) {
            adapterProcess.getProcess().destroy();
            return true;
        }
        return false;
    }
}