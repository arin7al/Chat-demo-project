package com.acme.edu.Command;

public class StringVarargCommand extends Command{
    private String[] message;

    public StringVarargCommand(String ... message) {
        this.message = message;
        this.state = "StringVarargArray";
    }

    public Integer getMessage() {   //исправить!!!!
        return 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int n = message.length;
        for (int i=0; i<n-1; i++) {
            sb.append(message[i]);
            sb.append("\n");
        }
        sb.append(message[n-1]);
        return sb.toString();
    }

    public void accumulate(Command prevCommand) {
    }

    public boolean shouldFlushNow(Command prevCommand) {
        return true;
    }
}
