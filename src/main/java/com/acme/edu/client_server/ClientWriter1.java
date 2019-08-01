package com.acme.edu.client_server;

import java.io.*;
import java.net.Socket;

public class ClientWriter1 {

    public static void main(String[] args) {
        int innerPort;
        /*try {
            innerPort = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("INCORRECT PORT");
            return;
        }*/
        innerPort = 998;
        try {
            final Socket server = new Socket("localhost", innerPort);
            final BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(
                                    new BufferedInputStream(
                                            server.getInputStream())));
            final BufferedWriter out =
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    new BufferedOutputStream(
                                            server.getOutputStream())));
            final BufferedWriter consoleWriter =
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    new BufferedOutputStream(System.out)));
            new readServerMessage(consoleWriter, in).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
