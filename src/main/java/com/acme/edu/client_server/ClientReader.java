package com.acme.edu.client_server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Java_5 on 01.08.2019.
 */
public class ClientReader {

    public static void main(String[] args) {
        int innerPort;
        try {
            innerPort = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("INCORRECT PORT");
            return;
        }
        try {
            final Socket server = new Socket("localhost", 666);
            final ServerSocket reroute = new ServerSocket(innerPort);
            final Socket rerouteClient = reroute.accept();
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
            final BufferedReader inReroute =
                    new BufferedReader(
                            new InputStreamReader(
                                    new BufferedInputStream(
                                            rerouteClient.getInputStream())));
            final BufferedWriter outReroute =
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    new BufferedOutputStream(
                                            rerouteClient.getOutputStream())));
            final BufferedReader consoleReader =
                    new BufferedReader(
                            new InputStreamReader(
                                    new BufferedInputStream(
                                            System.in)));


            new readConsoleMessage(consoleReader, out).start();
            new readServerMessage(outReroute, in).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
