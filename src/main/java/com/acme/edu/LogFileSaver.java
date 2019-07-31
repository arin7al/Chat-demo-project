package com.acme.edu;

import java.io.*;

/**
 * Created by Java_5 on 25.07.2019.
 */
public class LogFileSaver implements LogSaver {
    BufferedWriter out;

    public LogFileSaver(String path) throws FileException {
        File file = new File(path);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileException("Couldn't open the file. Check the path.");
        }

        try {
            this.out = new BufferedWriter(
                    new OutputStreamWriter(
                            new BufferedOutputStream(
                                    new FileOutputStream(
                                            file))));
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileException("Couldn't write in file");
        }
    }

    public void save(String message) throws FileException{
        try {
            out.write(message);
            out.newLine();

        } catch(IOException e) {
            e.printStackTrace();
            throw new FileException("Couldn't write in file");
        }
    }

    public void close() throws FileException {
        try {
            if (out != null) {
                out.close();
            }
        } catch(IOException e) {
            throw new FileException("Couldn't close file");
        }
    }
}
