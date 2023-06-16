package com.hudong.study.javanetwork.ch07;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

public class BinarySaver {
    public static void main(String[] args) {

        try {
            URL url = new URL("https://bitbucket.org/liule/snipaste/downloads/Snipaste-2.8.5-Beta-x64.zip");
            saveBinaryFile(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveBinaryFile(URL u) throws IOException {
        URLConnection uc = u.openConnection();
        String contentType = uc.getContentType();
        int contentLength = uc.getContentLength();
        if (contentType.startsWith("text/") || contentLength == -1) {
            throw new IOException("not a binary file");
        }
//        System.out.println(uc.getHeaderFields());
        System.out.println(uc.getHeaderFields());

        Date date = new Date(uc.getDate());
//        System.out.println(date);
        for (int i = 0; ; i++) {
            String header = uc.getHeaderField(i);
            if (header==null){
                break;
            }
            System.out.println(uc.getHeaderFieldKey(i)+":"+header);
        }
//        try (InputStream raw = uc.getInputStream()) {
//            BufferedInputStream in = new BufferedInputStream(raw);
//
//            byte[] data = new byte[contentLength];
//            int offset = 0;
//            while (offset < contentLength) {
//                int bytesRead = in.read(data, offset, data.length - offset);
//                if (bytesRead == -1) {
//                    break;
//                }
//                offset += bytesRead;
//            }
//
//            if (offset != contentLength) {
//                throw new IOException("only read " + offset + " bytes; Expected " + contentLength + "bytes");
//            }
//            String filename = u.getFile();
//            filename = filename.substring(filename.lastIndexOf("/") + 1);
//            try (FileOutputStream fout = new FileOutputStream(filename)) {
//                fout.write(data);
//                fout.flush();
//            }
//

//        }
    }
}
