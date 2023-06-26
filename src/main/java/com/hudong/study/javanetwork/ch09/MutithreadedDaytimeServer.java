package com.hudong.study.javanetwork.ch09;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class MutithreadedDaytimeServer {
    public static final int PORT = 13;

    public static void main(String[] args) {
        try (ServerSocket socket = new ServerSocket(PORT)) {
            while (true) {
                try {
                    Socket connect = socket.accept();// 此不能放入try-with-resources中
                    // 否则在主线程达到while的末尾就会关闭socket
                    Thread task = new DaytimeThread(connect);
                    task.start();
                } catch (IOException ex) {

                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private static class DaytimeThread extends Thread {
        private Socket connection;

        DaytimeThread(Socket connection) {
            this.connection = connection;
        }

        @Override
        public void run() {
            try {
                OutputStream out = connection.getOutputStream();
                Writer writer = new OutputStreamWriter(out);
                Date now = new Date();
                writer.write(now.toString() + "\r\n");
                writer.flush();
            } catch (IOException ex) {

            } finally {
                try {
                    connection.close();
                } catch (IOException e) {

                }
            }
        }
    }
}
