package com.hudong.study.javanetwork.ch07;

import com.hudong.study.javanetwork.ch05.QueryString;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FormPoster {

    private URL url;
    private QueryString query = new QueryString();

    public FormPoster(URL url) {
        if(!url.getProtocol().toLowerCase().startsWith("http")){
            throw new IllegalArgumentException(
                    "Posting only works for http URLs"
            );
        }
        this.url = url;
    }

    public void  add(String name, String value){
        query.add(name, value);
    }

    public URL getUrl() {
        return url;
    }

    public InputStream post() throws IOException{
        URLConnection uc = url.openConnection();
        System.out.println(uc.getPermission());
        uc.setDoOutput(true);
        try(OutputStreamWriter out = new OutputStreamWriter(uc.getOutputStream(), StandardCharsets.UTF_8)){
            // POST 行，Content-type首部、Content-length首部
            // 由URLConnection 发送。
            out.write(query.toString());
            out.write("\r\n");
        }
        return uc.getInputStream();
    }

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://www.cafeaulait.org/books/jnp4/postquery.phtml");

        FormPoster poster = new FormPoster(url);
        poster.add("name", "Elliotte Rusty Harold");
        poster.add("email", "erharo@ibiblio.org");
        System.out.println(poster.getUrl());
        try (InputStream in = poster.post()){
           Reader reader = new InputStreamReader(in);
           int c;
           while((c = reader.read())!=-1){
               System.out.print((char) c);
           }
            System.out.println();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
