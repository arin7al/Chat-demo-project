package com.acme.edu.client_server;

import com.acme.edu.commands.Command;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class MultiThreadedServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(666);

        HashSet<BufferedWriter> clientSet = new HashSet<>();
        Collection<BufferedWriter> clientSyncSet = Collections.synchronizedSet(clientSet);   //get and put will be safe

        Collection<Command> commands = Collections.synchronizedList(new LinkedList<>());
        String messageHistory = null;
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
                clientSyncSet.add(out);
                new ClientSession(client, in, out, messageHistory, clientSyncSet, commands).start();
                // new getMessage(client, in, out).start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



/*


}*/