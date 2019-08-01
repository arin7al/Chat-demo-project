package com.acme.edu.message;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    private String message;
    private String userID;
    private Date date;

    Message(String message, String userID) {
        this.message = message;
        this.userID = userID;
        this.date = new Date(System.currentTimeMillis());
    }

    public String getDecorate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm-dd 'at' HH:mm:ss z");
        String stringDate = simpleDateFormat.format(date);
        return stringDate + " " + userID + ": " + message;
    }

    @Override
    public String toString() {
        return getDecorate();
    }

    static boolean canBeMessage(String message) {
        if (message.length() > 150) {
            return false;
        }
        return true;
    }
}