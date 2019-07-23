package com.acme.edu.Command;

/**
 * Created by Java_5 on 19.07.2019.
 */
public class BoolCommand extends Command {
    private boolean message;

    public BoolCommand(boolean message) {
        this.message = message;
        this.state = "Bool";
    }

    public Boolean getMessage() {
        return this.message;
    }

    public String toString() {
        return ("primitive: " + message);
    }

    public void accumulate(Command prevCommand) {
    }

    public boolean shouldFlushNow(Command prevCommand) {
        return true;
    }
}
