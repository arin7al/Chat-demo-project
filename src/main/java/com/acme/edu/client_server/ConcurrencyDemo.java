package com.acme.edu.client_server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrencyDemo {
    public static void main(String[] args) {
        final ExecutorService pool = Executors.newFixedThreadPool(40);
        final Counter counter = new Counter();
        for (int i = 0; i < 100_000; i++) {
            pool.submit(counter::increment);
        }
        pool.shutdown();
        System.out.println(counter.getCount());
    }
}

class Counter {
    private volatile int count;
    private Lock l = new ReentrantLock(); //ReadWrite
    public synchronized void increment() {
        l.lock();
        try {
            count++; //<-, ?, ->
        }
        finally {
            l.unlock();
        }

    }

    public int getCount() {
        l.lock();
        try {
            return count;
        }
        finally {
            l.unlock();
        }
    }
}