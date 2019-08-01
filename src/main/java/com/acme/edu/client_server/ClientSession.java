package com.acme.edu.client_server;

import java.io.*;
import java.net.Socket;
import java.util.HashSet;
import com.acme.edu.commands.Command;
import com.acme.edu.message.Parser;
import com.acme.edu.commands.CommandSend;
import com.acme.edu.commands.CommandHist;
import com.acme.edu.commands.CommandUnknown;

public class ClientSession extends Thread {
    private Socket client;
    private BufferedReader in;
    private BufferedWriter out;
    private HashSet<BufferedWriter> clientOutList;
    private String historyMessage;

    public ClientSession(Socket client, BufferedReader in, BufferedWriter out,
                         String historyMessage, HashSet<BufferedWriter> clientOutList) throws IOException {
        this.client = client;
        this.in = in;
        this.out = out;
        this.clientOutList = clientOutList;
        this.historyMessage = historyMessage;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String inputLine = in.readLine();
                Command command = Parser.parse(inputLine, "new_user");
                if (command instanceof CommandSend) {
                    updateHistoryMessage(command.toString());
                    sendEverybody(command.toString());
                } else if (command instanceof CommandHist) {
                    sendHistory();
                } else if (command instanceof CommandUnknown) {
                    sendUnknownCommand();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void sendUnknownCommand() {
        send(this.out, "Command is incorrect. Repeat please!");
    }

    public void sendEverybody(String message) {
        for (BufferedWriter bw : clientOutList) {
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
            clientOutList.remove(out);
        }
    }

    public synchronized void updateHistoryMessage(String newMessage){
       historyMessage += newMessage;
    }
}
