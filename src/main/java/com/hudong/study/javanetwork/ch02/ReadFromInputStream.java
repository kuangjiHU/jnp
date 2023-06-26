package com.hudong.study.javanetwork.ch02;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReadFromInputStream {

    public static void mark(){
        log.info("InputStream 提供允许程序备份和重新读取数据已读数据");
        // mark\reset\markSupported()
        // readAheadLimit
        // 一个流只能有一个标记
        // 标记第一个清除第二个
        // 先检查markSupported()
        // BufferedInputStream
        // ByteArrayInputStream

    }

    public static void read(){
        try (InputStream in = new FileInputStream("SingleFileHTTPServerTest.txt.txt")) {
            byte[] input = new byte[10];
            int total = in.read(input);
            log.info("text:{}", new String(input));
            log.info("byte number:{}", total);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (InputStream in = new FileInputStream("SingleFileHTTPServerTest.txt.txt")) {
            byte[] input = new byte[10];
            for (int i = 0; i < input.length; i++) {
                int b = in.read();
                if (b == -1) {
                    log.info("EOF");
                    break;
                }

                input[i] = (byte) b;
            }
            log.info("text:{}", new String(input));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static void main(String[] args) {

//        read();
        mark();
    }
}
