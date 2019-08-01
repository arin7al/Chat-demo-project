package com.acme.edu.client_server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Created by Java_5 on 01.08.2019.
 */
public class readServerMessage extends Thread{
    BufferedWriter consoleWriter;
    BufferedReader in;
    String inputLine;
    readServerMessage(BufferedWriter consoleWriter, BufferedReader in) throws IOException {
        this.consoleWriter = consoleWriter;
        this.in = in;
    }

    @Override
    public void run() {
        while (true) {
            try {
                inputLine = in.readLine();
                String[] commandAndMessage = inputLine.split(" ");
                consoleWriter.write(inputLine);
                consoleWriter.newLine();
                consoleWriter.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

