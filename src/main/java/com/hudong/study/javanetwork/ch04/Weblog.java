package com.hudong.study.javanetwork.ch04;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Weblog {
    public static void main(String[] args) {
        try (FileInputStream fin = new FileInputStream("Weblog");
             InputStreamReader in = new InputStreamReader(fin);
             BufferedReader bin = new BufferedReader(in)) {
            for (String entry = bin.readLine(); entry != null; entry = bin.readLine()) {
                int i = entry.indexOf(' ');
                String ip = entry.substring(0, i);
                String theRest = entry.substring(i);
                try {
                    InetAddress address = InetAddress.getByName(ip);
                    System.out.println(address.getHostName() + theRest);
                }catch (UnknownHostException exception){
                    System.err.println(entry);
                }
            }
        } catch (IOException e) {
            System.err.println("Exception:" + e);
        }
    }
}
