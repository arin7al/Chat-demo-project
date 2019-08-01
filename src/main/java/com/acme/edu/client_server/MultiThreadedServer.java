package com.acme.edu.client_server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.HashSet;

public class MultiThreadedServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(666);

        Collection<BufferedWriter> clientOutList = new HashSet<>();
        String messageHistory = "";
        while (true) {
            try {
                final Socket client = serverSocket.accept();
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                new BufferedInputStream(
                                        client.getInputStream())));
                BufferedWriter out = new BufferedWriter(
                        new OutputStreamWriter(
                                new BufferedOutputStream(
                                        client.getOutputStream())));
                clientOutList.add(out);
                new ClientSession(in, out, messageHistory, clientOutList).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



/*


}*/