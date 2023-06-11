package com.hudong.study.javanetwork.ch04;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

public class IBiblioAliases {
    public static void main(String[] args) {
        try {
//            InetAddress a1 = InetAddress.getByName("www.ibiblio.org");
//            InetAddress a2 = InetAddress.getByName("helios.ibiblio.org");
//            if(a1.equals(a2)){
//                System.out.println("same");
//            }else {
//
//                System.out.println("not same");
//            }
            NetworkInterface ni = NetworkInterface.getByName("eth0");
            if(ni == null){
                System.out.println("No such interface");
            }else {
                System.out.println(ni);
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }
}
