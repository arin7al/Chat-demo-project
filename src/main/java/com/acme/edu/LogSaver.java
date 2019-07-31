package com.acme.edu;

public interface LogSaver {
    void save(String message) throws FileException;

    void close() throws FileException;
}
