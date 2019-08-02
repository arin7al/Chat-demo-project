package com.acme.edu.client_server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientReader1 {

    public static void main(String[] args) {
        int innerPort;
        Socket server = null;
        ServerSocket reroute = null;
        Socket rerouteClient = null;
        BufferedReader in = null;
        BufferedWriter out = null;
        BufferedReader inReroute = null;
        BufferedWriter outReroute = null;
        BufferedReader consoleReader = null;

        try {
            innerPort = 667;
            server = new Socket("localhost", 666);
            reroute = new ServerSocket(innerPort);
            rerouteClient = reroute.accept();
            in = new BufferedReader(
                    new InputStreamReader(
                            new BufferedInputStream(
                                    server.getInputStream())));
            out = new BufferedWriter(
                    new OutputStreamWriter(
                            new BufferedOutputStream(
                                    server.getOutputStream())));
            inReroute = new BufferedReader(
                    new InputStreamReader(
                            new BufferedInputStream(
                                    rerouteClient.getInputStream())));
            outReroute = new BufferedWriter(
                    new OutputStreamWriter(
                            new BufferedOutputStream(
                                    rerouteClient.getOutputStream())));
            consoleReader = new BufferedReader(
                    new InputStreamReader(
                            new BufferedInputStream(
                                    System.in)));


            new readConsoleMessage(consoleReader, out).start();
            new readServerMessage(outReroute, in).start();
        } catch (Exception e) {
            try {
            /*    if (consoleReader != null)
                consoleReader.close();
                if (outReroute != null)
                    outReroute.close();
                if (inReroute != null)
                    inReroute.close();
                if (rerouteClient != null)
                    rerouteClient.close();
                if (reroute != null)
                    reroute.close();
                if (out != null)
                    out.close();
                if (in != null)
                    in.close();
                if (server != null)
                    server.close();*/

            } catch (Exception ee) {
                ee.printStackTrace();
                System.out.println();
            }
        }
    }

}