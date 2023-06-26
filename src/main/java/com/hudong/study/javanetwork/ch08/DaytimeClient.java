package com.hudong.study.javanetwork.ch08;

import lombok.val;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class DaytimeClient {


    public static void main(String[] args) {
        String hostname = "localhost";
        try (Socket socket = new Socket(hostname, 37)) {
            socket.setSoTimeout(15000);
            InputStream in = socket.getInputStream();
            StringBuilder time = new StringBuilder();
            InputStreamReader reader = new InputStreamReader(in);
            for (int c = reader.read(); c!=-1; c= reader.read()){
                System.out.println(c);
                time.append(c);
            }
            System.out.println(time);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
