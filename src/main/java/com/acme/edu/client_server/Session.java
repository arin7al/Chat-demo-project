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

class Session extends Thread {
    Socket client;
    BufferedReader in;
    BufferedWriter out;
    LoggerController serverLogger = null;
    boolean keepGoing = true;
    String inputLine;

    void decorate(BufferedWriter out, String message) throws IOException {
        out.write(">>> " + message + " accepted");
        out.newLine();
        out.flush();
    }
}