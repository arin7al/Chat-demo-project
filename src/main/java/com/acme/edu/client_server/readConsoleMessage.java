package com.acme.edu.client_server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Created by Java_5 on 01.08.2019.
 */
public class readConsoleMessage extends Thread{
    BufferedReader consoleReader;
    BufferedWriter out;
    String inputLine;
    readConsoleMessage(BufferedReader consoleReader,BufferedWriter out) throws IOException {
        this.consoleReader = consoleReader;
        this.out = out;
    }

    @Override
    public void run() {
        while (true) {
            try {
                inputLine = consoleReader.readLine();
                String[] commandAndMessage = inputLine.split(" ");
                out.write(inputLine);
                out.newLine();
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
