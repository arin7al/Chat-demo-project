package com.acme.edu.message;

import com.acme.edu.commands.*;

public class Parser {

    public static Command parse(String message, String name) {
        String[] strings = message.split(" ");
        switch (strings[0]) {
            case "\\hist":
                return new CommandHist(name);
            case "\\snd":
                String newMessage = "";
                for(int i=1; i<strings.length; i++){
                    newMessage += strings[i];
                }
                return new CommandSend(newMessage, name);
            case "\\chid":
                return new CommandLogging();
            default:
                return new CommandUnknown();
        }
    }

}
