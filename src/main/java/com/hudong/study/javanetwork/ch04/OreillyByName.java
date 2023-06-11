package com.hudong.study.javanetwork.ch04;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class OreillyByName {

    public static void main(String[] args) {
        try {
            InetAddress[] address = null;
            InetAddress address2 = null;
            InetAddress address3 = null;
            address = InetAddress.getAllByName("www.baidu.com");
            System.out.println(Arrays.toString(address));
            address2 = InetAddress.getByName("36.152.44.95");
            System.out.println(address2.getHostName());
            address3 = InetAddress.getLocalHost();
            System.out.println(address3.getHostAddress());
            System.out.println(address3.isReachable(10));
        } catch (UnknownHostException e) {
            System.out.println("could not find www.baidu.com");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
