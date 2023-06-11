package com.hudong.study.javanetwork.ch04;

import java.io.*;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PooledWeblog {

    public static void main(String[] args) {
        ExecutorService excutors = Executors.newFixedThreadPool(4);
        LinkedList<LogEntry> results = new LinkedList<LogEntry>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])))) {
            for (String entry = reader.readLine(); entry != null; entry = reader.readLine()) {
                LookupTask task = new LookupTask(entry);
                Future<String> future = excutors.submit(task);
                results.add(new LogEntry(entry, future));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (LogEntry result : results) {
            try {
                System.out.println(result.future.get());
            } catch (InterruptedException | ExecutionException e) {
                System.out.println(result.original);
            }
        }

        excutors.shutdown();
    }

    private static class LogEntry {
        String original;
        Future<String> future;

        public LogEntry(String original, Future<String> future) {
            this.original = original;
            this.future = future;
        }
    }
}
