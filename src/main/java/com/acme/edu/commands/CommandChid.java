package com.acme.edu.commands;

public class CommandChid implements Command{
    private String userID;
    public CommandChid(String userID) {
        this.userID = userID;
    }
    public String getID() {
        return userID;
    }
}
