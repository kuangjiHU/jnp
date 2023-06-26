package com.hudong.study.javanetwork.ch09;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PooledDaytimeServer {
    public static final int PORT = 13;

    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(50);

        try (ServerSocket socket = new ServerSocket(PORT)) {
            while (true) {
                try {
                    Socket connect = socket.accept();// 此不能放入try-with-resources中
                    // 否则在主线程达到while的末尾就会关闭socket
//                    Thread task = new DaytimeThread(connect);
//                    task.start();
                    DaytimeTask task = new DaytimeTask(connect);
                    pool.submit(task);
                } catch (IOException ex) {

                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    private static class DaytimeTask implements Callable<Void> {
        private Socket connection;

        DaytimeTask(Socket connection) {
            this.connection = connection;
        }

        @Override
        public Void call() {
            try {
                OutputStream out = connection.getOutputStream();
                Writer writer = new OutputStreamWriter(out);
                Date now = new Date();
                writer.write(now.toString() + "\r\n");
                writer.flush();
            } catch (IOException ex) {

            }finally {

                try {
                    connection.close();
                } catch (IOException e) {

                }
            }
            return null;
        }
    }
}
