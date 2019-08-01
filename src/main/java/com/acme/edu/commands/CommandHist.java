package com.acme.edu.commands;

public class CommandHist implements Command{
    private String userID;
    CommandHist(String userID) {
        this.userID = userID;
    }
}
