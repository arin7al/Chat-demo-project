package com.acme.edu;


public class LogConsoleSaver implements LogSaver {
    public void save(String message) {
        System.out.println(message);
    }

    public void close() {
    }
}
