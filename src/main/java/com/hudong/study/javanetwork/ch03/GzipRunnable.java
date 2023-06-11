package com.hudong.study.javanetwork.ch03;

import java.io.*;
import java.util.zip.GZIPOutputStream;

public class GzipRunnable implements Runnable {

    private final File input;

    public GzipRunnable(File input) {
        this.input = input;
    }

    @Override
    public void run() {
        if (!input.getName().endsWith(".gz")) {
            File output = new File(input.getParent(), input.getName() + ".gz");
            if (!output.exists()) {
                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(input));
                     BufferedOutputStream out = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(output)))) {
                    int b;
                    while ((b = in.read()) != -1) {
                        out.write(b);
                        out.flush();
                    }
                } catch (IOException ex) {
                    System.err.println(ex);
                }
            }
        }
    }
}
