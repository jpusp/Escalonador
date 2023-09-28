package process;

import java.util.List;

public class BCP {
    private String name;
    private ProcessState processState;
    private int pc = 0;
    private int x = 0;
    private int y = 0;
    List<String> instructions;
    private int blockedWaitTime = 0;

    public BCP(String name, ProcessState state, List<String> instructions) {
        this.name = name;
        this.processState = state;
        this.instructions = instructions;
    }

    public String getName() {
        return name;
    }

    public ProcessState getState() {
        return processState;
    }

    public void setState(ProcessState processState) {
        this.processState = processState;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void decBlockWaitTime() {
        if (blockedWaitTime != 0) {
            blockedWaitTime--;
        }
    }

    public int getBlockedWaitTime() {
        return blockedWaitTime;
    }

    public void setBlockedWaitTime(int blockedWaitTime) {
        if (blockedWaitTime > 0) {
            this.blockedWaitTime = blockedWaitTime;
        }
    }

    public String getInstruction() {
        if (pc >= instructions.size()) return null;
        String instruction = instructions.get(pc);
        pc++;
        return instruction;
    }
}
