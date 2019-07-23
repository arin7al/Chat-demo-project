package com.acme.edu.Command;


public class ByteCommand extends Command{
    private byte message;

    public ByteCommand(byte message) {
        this.message = message;
        this.state = "Byte";
    }

    public Byte getMessage(){
        return this.message;
    }

    public String toString() {
        return("primitive: " + message);
    }

    public void accumulate(Command prevCommand) {
        if (prevCommand==null) return;
        this.message += (byte)prevCommand.getMessage();
    }

    public boolean isOverflowed(Command prevCommand) {
        int accumulatedSumm = (byte)prevCommand.getMessage();

        int diffPos = Byte.MAX_VALUE - accumulatedSumm;
        int diffNeg = Byte.MIN_VALUE - accumulatedSumm;

        boolean isPositiveOverflow = (accumulatedSumm >= 0) && (diffPos < message);
        boolean isNegativeOverflow = (accumulatedSumm < 0) && (diffNeg > message);

        return isPositiveOverflow || isNegativeOverflow;
    }

    public boolean shouldFlushNow(Command prevCommand) {
        return isOverflowed(prevCommand);
    }
}
