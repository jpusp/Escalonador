package process;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static process.ProcessState.*;

public class InstructionDecoder {
    private final static int FIELD = 1;
    private final static int VALUE = 2;
    private final int waitTime;
    private Pattern attributionPattern = Pattern.compile("(X|Y)=([0-9]+)");
    private Pattern ESPattern = Pattern.compile("^E\\/S$");

    public InstructionDecoder(int waitTime) {
        this.waitTime = waitTime;
    }

    public void decode(BCP process, String instruction) {
        Matcher attributionMatcher = attributionPattern.matcher(instruction);
        Matcher esMatcher = ESPattern.matcher(instruction);

        if (attributionMatcher.matches()) {
            attributeValue(process, attributionMatcher);
            process.setState(READY);
        } else if (esMatcher.matches()) {
            process.setBlockedWaitTime(waitTime);
            process.setState(BLOCKED);
        } else {
            process.setState(READY);
        }
    }

    private void attributeValue(BCP process, Matcher matcher) {
        int value = Integer.parseInt(matcher.group(VALUE));

        if ("X".equals(matcher.group(FIELD))) {
            process.setX(value);
        } else {
            process.setY(value);
        }
    }
}
