package com.acme.edu.Command;

import java.util.Objects;

public class StringCommand extends Command{
    private String message;

    private int numberOfIdenticalStr;

    public StringCommand(String message) throws NullMessageException{
        if (message == null) throw new NullMessageException("Can't log Null String");
        this.message = message;
        this.numberOfIdenticalStr = 1;
        this.state = "String";
    }

    public int getNumberOfIdenticalStr(){
        return numberOfIdenticalStr;
    }
    public String getMessage(){
        return this.message;
    }

    public String toString() {
        if (numberOfIdenticalStr == 1) {
            return("string: " + message);
        }
        return("string: " + message + " (x" + numberOfIdenticalStr + ")");
    }

    public void accumulate(Command prevCommand) {
        if (prevCommand !=null && !isDifferentString(prevCommand)) {
            this.numberOfIdenticalStr += ((StringCommand)prevCommand).getNumberOfIdenticalStr();
        }
    }

    private boolean isDifferentString(Command prevCommand) {
        return !Objects.equals(prevCommand.getMessage(), this.getMessage());
    }

    public boolean shouldFlushNow(Command prevCommand) {
        return isDifferentString(prevCommand);
    }
}
