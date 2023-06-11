package com.hudong.study.javanetwork.ch03;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GZipAllFiles {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(4);
        args = new String[]{
                "C:\\Users\\KJHu\\OneDrive\\文档\\ZTE\\入职"
        };
        for (String filename : args){

            File f = new File(filename);
            if(f.exists()){
                if(f.isDirectory()){
                    File[] files = f.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        if(!files[i].isDirectory()){
                            Runnable task = new GzipRunnable(files[i]);
                            pool.submit(task);
                        }
                    }
                }else {
                    GzipRunnable task = new GzipRunnable(f);
                    pool.submit(task);
                }
            }
        }
    }
}
