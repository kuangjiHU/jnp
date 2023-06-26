package com.hudong.study.javanetwork.ch09;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class DaytimeServer {
    public static final int PORT = 13;

    public static void main(String[] args) {
        try (ServerSocket socket = new ServerSocket( PORT)) {
            while (true) {

                try (Socket connect = socket.accept()) {
                    OutputStream out = connect.getOutputStream();
                    Writer writer = new OutputStreamWriter(out);
                    Date now = new Date();
                    writer.write(now.toString() + "\r\n");
                    writer.flush();
                } catch (IOException ex) {

                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
