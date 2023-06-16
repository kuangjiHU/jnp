package com.hudong.study.javanetwork.ch07;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class SourceViewer2 {

    public static void main(String[] args) {
        args = new String[]{
                "https://www.baidu.com"
        };
        if (args.length > 0) {
            try {
                URL url = new URL(args[0]);
                URLConnection uc = url.openConnection();
                System.out.println(uc.getHeaderFields());
                try (InputStream raw = uc.getInputStream();) {
                    InputStream buffer = new BufferedInputStream(raw);
                    Reader reader = new InputStreamReader(buffer);
                    int c;
                    while ((c = reader.read()) != -1) {
                        System.out.print((char) c);
                    }
                }

            } catch (MalformedURLException e) {
                System.err.println(args[0] + " is not a parseable URL");
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }
}
