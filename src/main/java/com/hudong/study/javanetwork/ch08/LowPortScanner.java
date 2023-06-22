package com.hudong.study.javanetwork.ch08;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class LowPortScanner {

    public static void main(String[] args) {
        String host = args.length > 0 ? args[0] : "localhost";
        for (int i = 1; i < 1024; i++) {
            try {
                Socket socket = new Socket(host, i);
                socket.setSoTimeout(10);
                System.out.println(
                        "There is a server on port " + i + " of " + host
                );
                socket.close();
            } catch (UnknownHostException ex) {
                System.err.println(ex);
                break;
            } catch (IOException e) {

            }
        }
    }
}
