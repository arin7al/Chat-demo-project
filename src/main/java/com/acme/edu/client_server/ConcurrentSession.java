package com.acme.edu.client_server;

import com.acme.edu.Command.ByteCommand;
import com.acme.edu.Command.CharCommand;
import com.acme.edu.Command.IntCommand;
import com.acme.edu.Command.StringCommand;
import com.acme.edu.FileException;
import com.acme.edu.LogFileSaver;
import com.acme.edu.LoggerController;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentSession extends Session {
    //private Lock l = new ReentrantLock();

    ConcurrentSession(Socket client, LoggerController serverLogger) throws IOException {
        this.client = client;

        in = new BufferedReader(
                new InputStreamReader(
                        new BufferedInputStream(
                                client.getInputStream())));
        out = new BufferedWriter(
                new OutputStreamWriter(
                        new BufferedOutputStream(
                                client.getOutputStream())));
        this.serverLogger = serverLogger;
    }
    @Override
    public void run() {

        while (keepGoing) {
            try {
                inputLine = in.readLine();
                String[] commandAndMessage = inputLine.split(" ");
                logMessage(commandAndMessage);

            } catch (NullPointerException|IOException e) {
                keepGoing = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    synchronized void logMessage(String[] commandAndMessage) throws Exception {
        //l.lock();
        synchronized (this) {
            switch (commandAndMessage[0]) {
                case ("int"): {
                    int intMessage = Integer.valueOf(commandAndMessage[1]);
                    serverLogger.log(new IntCommand(intMessage));
                    decorate(out, commandAndMessage[0]);
                    break;
                }
                case ("byte"): {
                    byte byteMessage = Byte.valueOf(commandAndMessage[1]);
                    serverLogger.log(new ByteCommand(byteMessage));
                    decorate(out, commandAndMessage[0]);
                    break;
                }
                case ("char"): {
                    if (commandAndMessage[1].length() > 1) {
                        throw new IOException("Illegal char value.");
                    }
                    char charMessage = commandAndMessage[1].charAt(0);
                    serverLogger.log(new CharCommand(charMessage));
                    decorate(out, commandAndMessage[0]);
                    break;
                }
                case ("string"): {
                    serverLogger.log(new StringCommand(commandAndMessage[1]));
                    decorate(out, commandAndMessage[0]);
                    break;
                }
                case ("close"): {
                    if (Thread.activeCount()==4) {
                        serverLogger.close();
                        decorate(out, commandAndMessage[0]+"!");
                    }
                    else {
                        decorate(out, commandAndMessage[0]+", waiting for the last process..." + Thread.activeCount());

                    }
                    break;
                }
                default: {
                    throw new IOException("Illegal operation type");
                }
            }
        }
        //l.unlock();
    }
}
