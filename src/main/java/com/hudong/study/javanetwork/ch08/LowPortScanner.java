package com.hudong.study.javanetwork.ch08;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class LowPortScanner {

    public static void main(String[] args) {
        String host = "192.168.1.14";
        for (int i = 1; i < 1024; i++) {
            try {
                Socket socket = new Socket(host, i);
                System.out.println(
                        "There is a server on port " + i + " of " + host
                );
                socket.close();
            } catch (UnknownHostException ex) {
                System.err.println(ex);
                break;
            } catch (IOException e) {
//                System.err.println(e);
            }
        }
    }
}
