package com.hudong.study.javanetwork.ch08;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

public class DictClient {

    private static final String SERVER = "dict.org";
    private static final int PORT = 2628;
    private static final int TIMEOUT = 15000;

    public static void main(String[] args) {
        args = new String[]{
                "gold",
                "uranium",
                "lead",
        };
        try (Socket socket = new Socket(SERVER, PORT)) {
            socket.setSoTimeout(TIMEOUT);
            OutputStream out = socket.getOutputStream();
            Writer writer = new OutputStreamWriter(out, StandardCharsets.UTF_8);
            InputStream in = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            for (String word : args) {
                define(word, writer, reader);
            }
            writer.write("quit\r\n");
            writer.flush();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void define(String word, Writer writer, BufferedReader reader) throws IOException {
        writer.write("DEFINE fd-eng-gle " + word + "\r\n");
        writer.flush();

        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            if (line.startsWith("250")) {
                return;
            } else if (line.startsWith("552")) {
                System.out.println("No definition found for " + word);
                return;
            } else if (line.matches("\\d\\d\\d .*")) {
                continue;
            } else if (line.trim().equals(".")) {
                continue;
            } else {
                System.out.println(line);
            }
        }
    }
}
