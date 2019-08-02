package com.acme.edu.commands;

import com.acme.edu.message.Message;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommandSend implements Command {
    private String message;
    private String userID;
    private Date date;

    public CommandSend(String message, String userID) {
        this.message = message;
        this.userID = userID;
        this.date = new Date(System.currentTimeMillis());
    }

    public String getDecorateMessage() {
        String[] strings= message.split(" ");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm-dd 'at' HH:mm:ss z");
        String stringDate = simpleDateFormat.format(date);
        StringBuilder strb = new StringBuilder();
        strb.append(stringDate);
        strb.append(" ");
        strb.append(userID);
        strb.append(": ");
        int n = strings.length;
        for(int i = 1; i<n; i++) {
            strb.append(strings[i]);
        }
        //return stringDate + " " + userID + ": " + message;
        return strb.toString();
    }

    @Override
    public String toString() {
        return getDecorateMessage();
    }

    public boolean isLoggedUser(){
        return userID.isEmpty();
    }

    static boolean canBeMessage(String message) {
        if (message.length() > 150) {
            return false;
        }
        return true;
    }
}
