package com.acme.edu;


import com.acme.edu.Command.*;

public class Logger {
    public static LoggerController LC;

    static {
        try {
            LC = new LoggerController(new LogFileSaver("test.txt"));
        } catch (FileException e) {
            e.printStackTrace();
        }
    }

    public static void flush() throws Exception{
        LC.flush();
    }

    public static void close() throws Exception{
        LC.close();
    }

    public static void log(int message) throws Exception {
        LC.log(new IntCommand(message));
    }

    public static void log(byte message) throws Exception {
        LC.log(new ByteCommand(message));
    }

    public static void log(char message) throws Exception {
        LC.log(new CharCommand(message));
    }

    public static void log(String message) throws Exception {
        LC.log(new StringCommand(message));
    }

    public static void log(boolean message) throws Exception {
        LC.log(new BoolCommand(message));
    }

    public static void log(Object message) throws Exception {
        LC.log(new ObjCommand(message));
    }

    public static void log(int[] message) throws Exception {
        LC.log(new IntArrayCommand(message));
    }

    public static void log(String ... message) throws Exception {
        LC.log(new StringVarargCommand(message));
    }
}





