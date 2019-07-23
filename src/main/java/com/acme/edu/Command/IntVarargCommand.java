package com.acme.edu.Command;

public class IntVarargCommand extends Command{
    private int[] message;

    public IntVarargCommand(int ... message) {
        this.message = message;
        this.state = "IntVarargArray";
    }

    public Integer getMessage() {   //исправить!!!!
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();



        return sb.toString();
    }

    public void accumulate(Command prevCommand) {
    }

    public boolean shouldFlushNow(Command prevCommand) {
        return true;
    }
}
