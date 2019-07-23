package com.acme.edu;


import com.acme.edu.Command.*;

public class Logger {
    private static LoggerController LC = new LoggerController(new LogConsoleSaver());

    public static void close() {
        LC.close();
    }

//  public static void log(int[] message) {
//
//  }

    public static void log(int message) {
        LC.log(new IntCommand(message));
    }

    public static void log(byte message) {
        LC.log(new ByteCommand(message));
    }

    public static void log(char message) {
        LC.log(new CharCommand(message));
    }

    public static void log(String message) {
        LC.log(new StringCommand(message));
    }

    public static void log(boolean message) {
        LC.log(new BoolCommand(message));
    }

    public static void log(Object message) {
        LC.log(new ObjCommand(message));
    }

    public static void log(int[] message) {
        LC.log(new IntArrayCommand(message));
    }

    public static void log(String ... message) {
        LC.log(new StringVarargCommand(message));
    }

}





