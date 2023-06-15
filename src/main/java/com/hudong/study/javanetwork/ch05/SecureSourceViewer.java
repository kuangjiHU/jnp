package com.hudong.study.javanetwork.ch05;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.URL;

public class SecureSourceViewer {

    public static void main(String[] args) {
        Authenticator.setDefault(new DialogAuthenticator());
        try {
            URL url = new URL("https://pan.baidu.com/");
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
        System.exit(0);
    }

}
