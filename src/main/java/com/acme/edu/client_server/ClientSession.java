package com.acme.edu.client_server;

import java.io.*;
import java.util.Collection;
import java.util.HashSet;

import com.acme.edu.commands.*;
import com.acme.edu.message.Parser;

public class ClientSession extends Thread {
    private String userName;
    private BufferedReader in;
    private BufferedWriter out;
    private Collection<BufferedWriter> bufferOuts;
    private String historyMessage;

    public ClientSession(BufferedReader in, BufferedWriter out,
                         String historyMessage, Collection<BufferedWriter> bufferOuts) throws IOException {
        this.userName = "";
        this.in = in;
        this.out = out;
        this.bufferOuts = bufferOuts;
        this.historyMessage = historyMessage;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String inputLine = in.readLine();
                Command command = Parser.parse(inputLine, userName);
                if (command instanceof CommandSend) {
                    updateHistoryMessage(command.toString());
                    sendEverybody(command.toString());
                } else if (command instanceof CommandHist) {
                    sendHistory();
                } else if (command instanceof CommandUnknown) {
                    sendUnknownCommand();
                } else if (command instanceof CommandAnonymous) {
                    sendUnregistered();
                } else if (command instanceof CommandInit) {
                    setUserName(command.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void sendUnknownCommand() {
        send(this.out, "Command is incorrect. Repeat please!");
    }

    private void sendUnregistered() {
        send(this.out, "Please register to start chatting!");
    }

    public void sendEverybody(String message) {
        for (BufferedWriter bw : bufferOuts) {
            send(bw, message);
        }
    }

    public void sendHistory() {
        send(this.out, this.historyMessage);
    }

    public void send(BufferedWriter bw, String message) {
        try {
            bw.write(message);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            bufferOuts.remove(out);
        }
    }

    public synchronized void updateHistoryMessage(String newMessage){
       historyMessage += newMessage + System.lineSeparator();
    }
}
