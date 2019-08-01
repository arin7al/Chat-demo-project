package com.acme.edu.command;

import com.acme.edu.message.Message;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommandSend implements Command {
    private String message;
    private String userID;
    private Date date;

    CommandSend(String message, String userID) {
        this.message = message;
        this.userID = userID;
        this.date = new Date(System.currentTimeMillis());
    }

    public String getDecorateMessage() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm-dd 'at' HH:mm:ss z");
        String stringDate = simpleDateFormat.format(date);
        return stringDate + " " + userID + ": " + message;
    }

    @Override
    public String toString() {
        return getDecorateMessage();
    }

    static boolean canBeMessage(String message) {
        if (message.length() > 150) {
            return false;
        }
        return true;
    }

    @Override
    public void run() {

    }
}
