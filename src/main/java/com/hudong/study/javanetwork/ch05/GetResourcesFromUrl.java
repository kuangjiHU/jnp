package com.hudong.study.javanetwork.ch05;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class GetResourcesFromUrl {

    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.baidu.com/");
            System.out.println(url.toExternalForm());
            InputStream in = url.openStream();
            int c;
            while ((c  =in.read())!=-1)
                System.out.write(c);
            in.close();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
