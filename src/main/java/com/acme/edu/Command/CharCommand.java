package com.acme.edu.Command;

public class CharCommand extends Command {
    private char message;

    public CharCommand(char message) {
        this.message = message;
        this.state = "Char";
    }

    public Character getMessage() {
        return this.message;
    }

    public String toString() {
        return ("char: " + message);
    }

    public void accumulate(Command prevCommand) {
    }

    public boolean shouldFlushNow(Command prevCommand) {
        return true;
    }
}
