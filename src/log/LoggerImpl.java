package log;

import process.BCP;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LoggerImpl implements Logger {
    int quantum;

    public LoggerImpl(int quantum) {
        this.quantum = quantum;
    }

    @Override
    public void logExecution(BCP process) {
        log("Executando " + process.getName());
    }

    @Override
    public void logES(BCP process) {
        log("E/S iniciada em " + process.getName());
    }

    @Override
    public void logInterruption(BCP process, int executedInstructions) {
        log("Interrompendo " + process.getName() + " após " + executedInstructions + " instruções");
    }

    @Override
    public void logFinish(BCP process) {
        log(process.getName() + " terminado. X=" + process.getX() + ". Y=" + process.getY());
    }

    @Override
    public void log(String message) {
        System.out.println(message);

        String filename = getFileName(quantum);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFileName(int quantum) {
        String suffix = quantum < 10 ? "0" : "";
        return "log" + suffix + quantum + ".txt";
    }
}
