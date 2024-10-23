package lensjudge.process;

import java.time.Instant;

/**
 * The ProcessTimer class is responsible for monitoring the execution time of a process
 * and terminating it if it exceeds a specified timeout.
 */
public class ProcessTimer {
    private AdapterProcess adapterProcess;
    private long timeOut;

    /**
     * Constructs a new ProcessTimer with the specified AdapterProcess and timeout.
     *
     * @param adapterProcess the AdapterProcess to be monitored.
     * @param timeOut the timeout in milliseconds.
     */
    public ProcessTimer(AdapterProcess adapterProcess, long timeOut) {
        this.adapterProcess = adapterProcess;
        this.timeOut = timeOut;
    }

    /**
     * Checks if the process has timed out.
     * If the process is still running after the timeout period, it will be terminated.
     *
     * @return true if the process timed out and was terminated, false otherwise.
     */
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