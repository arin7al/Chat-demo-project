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
        Socket server = null;
        ServerSocket reroute = null;
        Socket rerouteClient = null;
        BufferedReader in = null;
        BufferedWriter out = null;
        BufferedReader inReroute = null;
        BufferedWriter outReroute = null;
        BufferedReader consoleReader = null;
        try {
            innerPort = Integer.parseInt(args[0]);
        } catch (Exception e) {
            System.out.println("INCORRECT PORT");
            return;
        }
        try {
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


            new ReadConsoleMessage(consoleReader, out).start();
            new ReadServerMessage(outReroute, in).start();
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
