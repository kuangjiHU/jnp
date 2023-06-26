package com.hudong.study.javanetwork.ch09;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Redirector {

    public static final Logger logger = Logger.getLogger("Redirector");
    private final int port;
    private final String newSite;


    public Redirector(String newSite, int port) {

        this.port = port;
        this.newSite = newSite;
    }


    public void start() {
        ExecutorService pool = Executors.newFixedThreadPool(100);
        try (ServerSocket server = new ServerSocket(this.port)) {
            logger.info("Redirect connections on port " + server.getLocalPort() +
                    " to " + newSite);
            while (true) {
                try {
                    Socket connection = server.accept();
                    pool.submit(new RedirectorHandler(connection));
                } catch (IOException ex) {
                    logger.log(Level.WARNING, "Exception accepting connection", ex);
                }
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Unexpected error", e);
        }
    }

    private class RedirectorHandler implements Callable<Void> {
        private final Socket connection;

        private RedirectorHandler(Socket connection) {
            this.connection = connection;
        }

        @Override
        public Void call() throws IOException {
            try {
                Writer out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), StandardCharsets.US_ASCII));
                Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder rqst = new StringBuilder(80);
                while (true) {
                    int c = in.read();
                    if (c == '\r' || c == '\n' || c == -1) {
                        break;
                    }
                    rqst.append((char) c);
                }
                String get = rqst.toString();
                String[] pieces = get.split("\\w*");
                String theFile = pieces[1];
                if (rqst.toString().contains("HTTP/")) {
                    out.write("HTTP/1.0 302 FOUND\r\n");
                    Date now = new Date();
                    out.write("Date: " + now + "\r\n");
                    out.write("Location: " + newSite + theFile + "\r\n");
                    out.write("content-type: text/plain\r\n\r\n");
                    out.flush();
                }
                // Not all browsers support redirection so we need to
                // produce HTML that says where the document has moved to.
                out.write("<HTML><HEAD><TITLE>Document moved</TITLE></HEAD>\r\n");
                out.write("<BODY><H1>Document moved</H1>\r\n");
                out.write("The document " + theFile
                        + " has moved to\r\n<A HREF=\"" + newSite + theFile + "\">"
                        + newSite + theFile
                        + "</A>.\r\n Please update your bookmarks<P>");
                out.write("</BODY></HTML>\r\n");
                out.flush();
                logger.log(Level.INFO,
                        "Redirected " + connection.getRemoteSocketAddress());
            } catch (IOException ex) {
                logger.log(Level.WARNING, "Error writing to client", ex);
            } finally {
                connection.close();
            }
            return null;
        }
    }

    public static void main(String[] args) {
        int port = 8129;
        Redirector server = new Redirector("http://www.baidu.com", port);
        server.start();
    }
}
