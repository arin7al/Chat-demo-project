package com.acme.edu.command;

public class CommandHist implements Command{
    private String userID;
    CommandHist(String userID) {
        this.userID = userID;
    }
}
