package com.acme.edu.client_server;

import java.io.*;
import java.net.Socket;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;

import com.acme.edu.commands.*;
import com.acme.edu.message.Parser;

public class ClientSession extends Thread {
    private Socket client;
    private BufferedReader in;
    private BufferedWriter out;
    private Collection<BufferedWriter> clientsOut;
    private Collection<Command> commands;
    private volatile String historyMessage;
    boolean keepGoing = true;
    String userName = "Anonymous";

    public ClientSession(Socket client, BufferedReader in, BufferedWriter out,
                         String historyMessage, Collection<BufferedWriter> clientOut, Collection<Command> commands) throws IOException {
        this.client = client;
        this.in = in;
        this.out = out;
        this.clientsOut = clientOut;
        this.historyMessage = historyMessage;
        this.commands = commands;
    }

    @Override
    public void run() {
        boolean nameNotIdentified = true;
        String inputLine = null;
        while (nameNotIdentified) {
            try {
                inputLine = in.readLine();
                Command command = Parser.parse(inputLine, "new_user");
                if (command instanceof CommandChid) {
                    nameNotIdentified = false;
                    userName = ((CommandChid) command).getID();
                } else {
                    sendPleaseLogIn();
                }
            } catch (Exception e) {
                if (inputLine == null) {
                    deleteCurrClient();
                }
                keepGoing = false;
                break;
            }
        }
        while (keepGoing) {
            try {
                inputLine = in.readLine();
                Command command = Parser.parse(inputLine, userName);
                if (command instanceof CommandSend) {
                    //updateHistoryMessage(command.toString());
                    commands.add(command);
                    sendEverybody(command.toString());
                } else if (command instanceof CommandHist) {
                    sendHistory();
                } else if (command instanceof CommandUnknown) {
                    sendUnknownCommand();
                }
            } catch (Exception e) {
                if (inputLine == null) {
                    deleteCurrClient();
                }
                break;
            }
        }
    }

    private synchronized void deleteCurrClient() {
        clientsOut.remove(out);
        keepGoing = false;
        System.out.println(userName + " disconnected");
        try {
            in.close();
            out.close();
        }
        catch (Exception e){
            System.out.println("Cannot read message");
        }
    }

    private void sendPleaseLogIn() {
        send(this.out, "You didn't log in. Log in please!");
    }

    private void sendUnknownCommand() {
        send(this.out, "Command is incorrect. Repeat please!");
    }

    public void sendEverybody(String message) {
        for (BufferedWriter bw : clientsOut) {
            send(bw, message);
        }
    }

    public void sendHistory() {
        send(this.out, "-----history begins-----");
        for (Command cmd: commands) {
            send(this.out, cmd.toString());
        }
        //send(this.out, this.historyMessage);
        send(this.out, "-----history ends-----");
    }

    public void send(BufferedWriter bw, String message) {
        try {
            bw.write(message);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            clientsOut.remove(out);
        }
    }

    public synchronized void updateHistoryMessage(String newMessage){
       historyMessage += newMessage;
    }
}
