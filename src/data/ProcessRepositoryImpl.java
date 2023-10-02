package data;

import log.Logger;
import process.BCP;
import process.ProcessState;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProcessRepositoryImpl implements ProcessRepository {
    private Logger logger;

    public ProcessRepositoryImpl(Logger logger) {
        this.logger = logger;
    }

    @Override
    public List<BCP> loadProcesses() {
        List<BCP> processes = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            String fileName = String.format("/resources/%02d.txt", i);
            BCP process = loadProcessFile(fileName);
            if (process != null) {
                logger.logProcessLoading(process);
                processes.add(process);
            }
        }
        return processes;
    }

    private BCP loadProcessFile(String fileName) {
        try (InputStream in = getClass().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
             String name = reader.readLine();
             List<String> instructions = new ArrayList<>();
             String line;

             while ((line = reader.readLine()) != null) {
                 instructions.add(line);
             }

            return new BCP(name, ProcessState.READY, instructions);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
