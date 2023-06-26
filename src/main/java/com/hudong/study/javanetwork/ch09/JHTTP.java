package com.hudong.study.javanetwork.ch09;

import com.sun.security.ntlm.Server;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JHTTP {
    private static final Logger logger = Logger.getLogger(JHTTP.class.getCanonicalName());
    private static final int NUM_THREADS = 50;
    private static final String INDEX_FILE = "index.html";
    private final File rootDirectory;
    private final int port;

    public JHTTP(File rootDirectory, int port) throws IOException {
        if (!rootDirectory.isDirectory()) {
            throw new IOException(rootDirectory + " does not exist  as a directory");
        }
        this.rootDirectory = rootDirectory;
        this.port = port;
    }

    public void start() throws IOException {
        ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);
        try (ServerSocket server = new ServerSocket(port)) {
            logger.info("Accepting connections on port " + server.getLocalPort());
            logger.info("Data be sent:");
            while (true) {
                try {
                    Socket request = server.accept();
                    Runnable processor = new RequestProcessor(rootDirectory, INDEX_FILE, request);
                    pool.submit(processor);
                } catch (IOException e) {
                    logger.log(Level.WARNING, "Error accepting connection", e);
                }
            }
        }
    }

    public static void main(String[] args) {
        File docRoot = new File("src/main/resources/");
        try {
            JHTTP sever = new JHTTP(docRoot, 8129);
            sever.start();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Usage: java JHTTP docroot port");
        }
    }
}
