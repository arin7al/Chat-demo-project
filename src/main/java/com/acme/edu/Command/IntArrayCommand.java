package com.acme.edu.Command;

public class IntArrayCommand extends Command {
    private int[] message;

    public IntArrayCommand(int[] message) {
        this.message = message;
        this.state = "IntArray";
    }

    public Integer getMessage() {   //исправить
        return this.message[0];
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("primitives array: ");
        sb.append("{");
        int n = message.length;
        for (int i=0; i<n-1; i++) {
            sb.append(message[i]);
            sb.append(", ");
        }
        sb.append(message[n-1]);
        sb.append("}");
        return sb.toString();
    }

    public void accumulate(Command prevCommand) {
    }

    public boolean shouldFlushNow(Command prevCommand) {
        return true;
    }
}
