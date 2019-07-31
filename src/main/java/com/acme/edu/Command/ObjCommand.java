package com.acme.edu.Command;

/**
 * Created by Java_5 on 19.07.2019.
 */
public class ObjCommand extends Command {
    private Object message;

    public ObjCommand(Object message) throws NullMessageException{
        if (message == null) throw new NullMessageException("Can't log Null Object");
        this.message = message;
        this.state = "Obj";
    }


    public Object getMessage() {
        return this.message;
    }

    public String toString() {
        return ("reference: " + message.toString());
    }

    public void accumulate(Command prevCommand) {
    }

    public boolean shouldFlushNow(Command prevCommand) {
        return true;
    }
}
