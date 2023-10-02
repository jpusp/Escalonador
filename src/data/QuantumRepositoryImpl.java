package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class QuantumRepositoryImpl implements QuantumRepository {
    @Override
    public int loadQuantum() {
        String fileName = "/resources/quantum.txt";
        try (InputStream in = getClass().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            return Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
