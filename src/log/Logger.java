package log;

import process.BCP;

public interface Logger {
    void logExecution(BCP process);

    void logES(BCP process);

    void logInterruption(BCP process, int executedInstructions);

    void logFinish(BCP process);

    void log(String message);

}
