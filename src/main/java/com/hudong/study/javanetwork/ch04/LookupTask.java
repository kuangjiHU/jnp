package com.hudong.study.javanetwork.ch04;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.Callable;

public class LookupTask implements Callable<String> {
    private String line;

    public LookupTask(String line) {
        this.line = line;
    }

    @Override
    public String call() throws Exception {
        try {
            int i = line.indexOf(' ');
            String ip = line.substring(0, i);
            String theRest = line.substring(i);
            String hostName = InetAddress.getByName(ip).getHostName();
            return hostName + " " + theRest;
        } catch (UnknownHostException exception) {
            return line;
        }
    }
}