package com.acme.edu.message;

import com.acme.edu.commands.*;

public class Parser {

    public static Command parse(String message, String name) {
        String[] strings = message.split(" ");
        switch (strings[0]) {
            case "\\hist":
                return new CommandHist(name);
            case "\\snd":
                return new CommandSend(message, name);
            case "\\chid":
                return new CommandChid(strings[1]);
            default:
                return new CommandUnknown();
        }
    }

}
