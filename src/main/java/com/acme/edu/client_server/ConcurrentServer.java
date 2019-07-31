package com.acme.edu.client_server;

import com.acme.edu.Command.*;
import com.acme.edu.FileException;
import com.acme.edu.LogFileSaver;
import com.acme.edu.LoggerController;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(666);
        try {
            LoggerController serverLogger = new LoggerController(new LogFileSaver(Thread.currentThread().getName()+".txt"));
            new Thread(() -> {
                while (true) {
                    try {
                        final Socket client = serverSocket.accept();
                        new ConcurrentSession(client, serverLogger).start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (FileException e) {
            e.printStackTrace();
        }
    }
}