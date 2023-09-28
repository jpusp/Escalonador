package log;

public class LoggerImpl implements Logger {
    @Override
    public void log(String message) {
        System.out.println(message);
    }
}
