package com.acme.edu.message;

import com.acme.edu.commands.*;

public class Parser {

    public static Command parse(String message, String name) {
        String[] strings = message.split(" ");
        String command = strings[0];
        String info = "";
        for(int i=1;i<strings.length;i++){
            info += " " + strings[i];
        }
        if (!name.isEmpty()) {
            switch (command) {
                case "\\hist":
                    return new CommandHist(name);
                case "\\snd":
                    return new CommandSend(info, name);
                default:
                    return new CommandUnknown();
            }
        } else {
            switch (command) {
                case "\\child":
                    return new CommandInit(info);
                default:
                    return new CommandAnonymous();
            }
        }
    }

}
