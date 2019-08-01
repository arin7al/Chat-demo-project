package com.acme.edu.message;

import com.acme.edu.commands.Command;
import com.acme.edu.commands.CommandHist;

public class Parser {

    public static Command parse(String message, String name) {
        String[] strings = message.split(" ");
        switch (strings[0]) {
            case "\\hist":
                return new CommandHist(name);
            case "\\send":
                return CommandSend(message, name);
            default:
                return CommandUknown();
        }
    }

}
