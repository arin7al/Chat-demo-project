package com.acme.edu.client_server;

import java.io.*;
import java.net.Socket;

/**
 * Created by Java_5 on 01.08.2019.
 */
public class ClientWriter {

    public static void main(String[] args) {
        int innerPort;
        Socket server = null;
        BufferedReader in = null;
        BufferedWriter out = null;
        BufferedWriter consoleWriter = null;
        try {
            innerPort = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("INCORRECT PORT");
            return;
        }
        try {
            server = new Socket("localhost", innerPort);
            in = new BufferedReader(
                            new InputStreamReader(
                                    new BufferedInputStream(
                                            server.getInputStream())));
            out = new BufferedWriter(
                            new OutputStreamWriter(
                                    new BufferedOutputStream(
                                            server.getOutputStream())));
            consoleWriter = new BufferedWriter(
                                new OutputStreamWriter(
                                    new BufferedOutputStream(System.out)));
            new ReadServerMessage(consoleWriter, in).start();
        } catch (IOException e) {
            e.printStackTrace();
/*            try {
 *//*               if (consoleWriter != null) {
                    consoleWriter.close();
                }
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
                if (server != null) {
                    server.close();
                }*//*
            } catch (IOException e1) {
                e1.printStackTrace();
                System.out.println("ERROR CLOSING");*/
      /*      }*/
        }
    }
}
