package com.hudong.study.javanetwork.ch07;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SourceViewer4 {
    public static void main(String[] args) {
        args = new String[]{
                "https://sp1.baidu.com/5b11fzupBgM18t7jm9iCKT-xh_/sensearch?wd=AOL&cb=bd_cb_dict3_1687098630049&callback=bd_cb_dict3_1687098630049&_=1687098629919"
        };
        if (args.length > 0) {
            try {
                URL url = new URL(args[0]);
                HttpURLConnection uc = (HttpURLConnection) url.openConnection();
                int code = uc.getResponseCode();
                String response = uc.getResponseMessage();
                System.out.println();
                if(uc.getInstanceFollowRedirects())
                    uc.setInstanceFollowRedirects(true);
                System.out.println("HTTP/1.x " + code + " " + response);
                for (int i = 1; ; i++) {
                    String header = uc.getHeaderField(i);
                    String key = uc.getHeaderFieldKey(i);
                    if (header == null || key == null) break;
                    System.out.println(uc.getHeaderFieldKey(i) + ": " + header);
                }
                System.out.println();
                try (InputStream raw = uc.getInputStream();) {
                    InputStream buffer = new BufferedInputStream(raw);
                    Reader reader = new InputStreamReader(buffer);
                    int c;
                    while ((c = reader.read()) != -1) {
                        System.out.print((char) c);
                    }
                } catch (IOException e) {
                    printFormStream(uc.getErrorStream());
                }

            } catch (MalformedURLException e) {
                System.err.println(args[0] + " is not a parseable URL");
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    private static void printFormStream(InputStream raw) throws IOException {
        try (InputStream buffer = new BufferedInputStream(raw)) {
            Reader reader = new InputStreamReader(buffer);
            int c;
            while ((c = reader.read()) != -1) {
                System.out.print((char) c);
            }
        }
    }
}
