package com.acme.edu.Command;

import java.util.Objects;

public abstract class Command {
    public String state;

    public boolean isChanged(Command prevCommand) {
        return !Objects.equals(this.state, prevCommand.state);
    }

    public abstract String toString();

    public abstract void accumulate(Command prevCommand);

    public abstract boolean shouldFlushNow(Command prevCommand);

    public abstract Object getMessage();
}