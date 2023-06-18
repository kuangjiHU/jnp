package com.hudong.study.javanetwork.ch07;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class LastModified {

    public static void main(String[] args) {
        try {
            URL url = new URL("http://www.ibiblio.org/xml");
            HttpURLConnection http = (HttpURLConnection)url.openConnection();
//            http.setRequestMethod("HEAD");
            http.setRequestMethod("TRACE");
//            http.setRequestMethod("OPTIONS");
            System.out.println(http.getResponseMessage());
            System.out.println(http.getHeaderFields());
            System.out.println(url + " was last modified at "+ new Date(http.getLastModified()));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
