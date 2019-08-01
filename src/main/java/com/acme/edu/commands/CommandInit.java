package com.acme.edu.commands;

public class CommandInit implements Command {
    private String name;

    public String getName() {
        return name;
    }

    public CommandInit(String name) {
        this.name = name;
    }

    public String toString(){
        return getName();
    }
}
