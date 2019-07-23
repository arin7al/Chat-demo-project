package com.acme.edu.Command;

public class IntCommand extends Command {
    private int message;

    public IntCommand(int message) {
        this.message = message;
        this.state = "Int";
    }

    public Integer getMessage(){
        return this.message;
    }

    public String toString() {
        return("primitive: " + message);
    }

    public void accumulate(Command prevCommand) {
        if (prevCommand==null) return;
        this.message += (int)prevCommand.getMessage();
    }

    public boolean isOverflowed(Command prevCommand) {
        int accumulatedSumm = (int)prevCommand.getMessage();

        int diffPos = Integer.MAX_VALUE - accumulatedSumm;
        int diffNeg = Integer.MIN_VALUE - accumulatedSumm;

        boolean isPositiveOverflow = (accumulatedSumm >= 0) && (diffPos < message);
        boolean isNegativeOverflow = (accumulatedSumm < 0) && (diffNeg > message);

        return isPositiveOverflow || isNegativeOverflow;
    }

    public boolean shouldFlushNow(Command prevCommand) {
        return isOverflowed(prevCommand);
    }
}
