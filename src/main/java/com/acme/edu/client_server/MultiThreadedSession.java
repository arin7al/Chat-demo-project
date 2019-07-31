package com.acme.edu.client_server;

import com.acme.edu.Command.ByteCommand;
import com.acme.edu.Command.CharCommand;
import com.acme.edu.Command.IntCommand;
import com.acme.edu.Command.StringCommand;
import com.acme.edu.FileException;
import com.acme.edu.LogFileSaver;
import com.acme.edu.LoggerController;

import java.io.*;
import java.net.Socket;

public class MultiThreadedSession extends Session  {
    MultiThreadedSession(Socket client) throws IOException {
        this.client = client;

        in = new BufferedReader(
                new InputStreamReader(
                        new BufferedInputStream(
                                client.getInputStream())));
        out = new BufferedWriter(
                new OutputStreamWriter(
                        new BufferedOutputStream(
                                client.getOutputStream())));
    }
    @Override
    public void run() {
        try {
            serverLogger = new LoggerController(new LogFileSaver(Thread.currentThread().getName()+".txt"));
        } catch (FileException e) {
            e.printStackTrace();
        }
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

    void logMessage(String[] commandAndMessage) throws Exception {
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
                serverLogger.close();
                decorate(out, commandAndMessage[0]);
                break;
            }
            default: {
                throw new IOException("Illegal operation type");
            }
        }
    }
}
