package com.hudong.study.javanetwork.ch05;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class FMoz {
    public static void main(String[] args) {
        args = new String[]{
                "computers"
        };
        String target = "";
        for (int i = 0; i < args.length; i++) {
            target += args[i] + " ";
        }
        target.trim();
//        QueryString query = new QueryString();
//        query.add("q", target);

        try {
            URL url = new URL("https://dmoz-odp.org/search?" + target);
            try (InputStream in = new BufferedInputStream(url.openStream())) {
                InputStreamReader theHTML = new InputStreamReader(in);
                int c;
                while ((c = theHTML.read()) != -1) {
                    System.out.println(c);
                }
            } catch (IOException e) {
                System.err.println(e);
            }
        } catch (MalformedURLException e) {
            System.err.println(e);
        }
    }
}
