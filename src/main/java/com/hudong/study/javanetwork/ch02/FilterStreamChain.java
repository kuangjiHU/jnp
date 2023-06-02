package com.hudong.study.javanetwork.ch02;

import java.io.*;

public class FilterStreamChain {

    public static void main(String[] args) {
        byte[] input = new byte[1024];
        try (InputStream in = new BufferedInputStream(new FileInputStream("test.txt"));
             DataOutputStream dout = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("test.txt")))) {
            // 过滤器流不要对同源流进行操作，选择一个
            in.read(input);
            dout.write(input);
            dout.write('\r');
            dout.write('\n');
            dout.write(65);
            dout.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
