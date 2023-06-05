package com.hudong.study.javanetwork.ch03;

import javax.xml.bind.DatatypeConverter;

public class ReturnDigestUserInterface {

    public static void main(String[] args) {
        args = new String[]{
                "C:\\Users\\KJHu\\OneDrive\\文档\\ZTE\\入职\\毕业生相关材料\\（双面打印）应聘人员信息登记表-（中文版）20200724.pdf",
                "C:\\Users\\KJHu\\OneDrive\\文档\\ZTE\\入职\\毕业生相关材料\\06.个人简历05.jpg",
                "C:\\Users\\KJHu\\OneDrive\\文档\\ZTE\\入职\\毕业生相关材料\\06.个人简历04.jpg",
                "C:\\Users\\KJHu\\OneDrive\\文档\\ZTE\\入职\\毕业生相关材料\\06.个人简历03.jpg",
                "C:\\Users\\KJHu\\OneDrive\\文档\\ZTE\\入职\\毕业生相关材料\\06.个人简历02.jpg",
                "C:\\Users\\KJHu\\OneDrive\\文档\\ZTE\\入职\\毕业生相关材料\\06.个人简历01.jpg"
        };
        ReturnDigests[] digests = new ReturnDigests[args.length];
        for (int i = 0; i < args.length; i++) {
            String f = args[i];

            ReturnDigests dr = new ReturnDigests(f);
            digests[i] = dr;
            digests[i].start();


        }
        for (int i = 0; i < args.length; i++) {
            StringBuilder result = new StringBuilder(args[i]);
            result.append(": ");
            result.append(DatatypeConverter.printHexBinary(digests[i].getDigest()));
            // 然而得到的结果仍然可能是异常的，
            // 取决于：程序生成了多少的线程，系统的CPU和磁盘的速度，
            // 系统使用了多少个CPU,以及JAVA虚拟机为不同线程分配的事件所用的算法。
            // 这些因素称为竞态条件。
            System.out.println(result);
        }
    }
}
