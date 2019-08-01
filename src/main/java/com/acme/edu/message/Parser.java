package com.acme.edu.message;

import com.acme.edu.commands.Command;
import com.acme.edu.commands.CommandHist;
import com.acme.edu.commands.CommandSend;
import com.acme.edu.commands.CommandUnknown;

public class Parser {

    public static Command parse(String message, String name) {
        String[] strings = message.split(" ");
        switch (strings[0]) {
            case "\\hist":
                return new CommandHist(name);
            case "\\snd":
                return new CommandSend(message, name);
            default:
                return new CommandUnknown();
        }
    }

}
