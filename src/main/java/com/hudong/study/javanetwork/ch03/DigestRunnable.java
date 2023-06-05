package com.hudong.study.javanetwork.ch03;

import javax.xml.bind.DatatypeConverter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestRunnable implements Runnable {
    private String filename;

    public DigestRunnable(String filename) {
        this.filename = filename;
    }

    @Override
    public void run() {
        try {
            FileInputStream in = new FileInputStream(filename);
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            DigestInputStream din = new DigestInputStream(in, sha);
            while (din.read() != -1) {

            }
            din.close();
            byte[] digest = sha.digest();
            synchronized (System.out){
                System.out.print(filename + ":");
                System.out.print(DatatypeConverter.printHexBinary(digest));
                System.out.print("\n");
            }
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }


    public static void main(String[] args) {
        args = new String[]{
                "C:\\Users\\KJHu\\OneDrive\\文档\\ZTE\\入职\\毕业生相关材料\\（双面打印）应聘人员信息登记表-（中文版）20200724.pdf",
                "C:\\Users\\KJHu\\OneDrive\\文档\\ZTE\\入职\\毕业生相关材料\\06.个人简历05.jpg",
                "C:\\Users\\KJHu\\OneDrive\\文档\\ZTE\\入职\\毕业生相关材料\\06.个人简历04.jpg",
                "C:\\Users\\KJHu\\OneDrive\\文档\\ZTE\\入职\\毕业生相关材料\\06.个人简历03.jpg",
                "C:\\Users\\KJHu\\OneDrive\\文档\\ZTE\\入职\\毕业生相关材料\\06.个人简历02.jpg","C:\\Users\\KJHu\\OneDrive\\文档\\ZTE\\入职\\毕业生相关材料\\（双面打印）应聘人员信息登记表-（中文版）20200724.pdf",
                "C:\\Users\\KJHu\\OneDrive\\文档\\ZTE\\入职\\毕业生相关材料\\06.个人简历05.jpg",
                "C:\\Users\\KJHu\\OneDrive\\文档\\ZTE\\入职\\毕业生相关材料\\06.个人简历04.jpg",
                "C:\\Users\\KJHu\\OneDrive\\文档\\ZTE\\入职\\毕业生相关材料\\06.个人简历03.jpg",
                "C:\\Users\\KJHu\\OneDrive\\文档\\ZTE\\入职\\毕业生相关材料\\06.个人简历02.jpg","C:\\Users\\KJHu\\OneDrive\\文档\\ZTE\\入职\\毕业生相关材料\\（双面打印）应聘人员信息登记表-（中文版）20200724.pdf",
                "C:\\Users\\KJHu\\OneDrive\\文档\\ZTE\\入职\\毕业生相关材料\\06.个人简历05.jpg",
                "C:\\Users\\KJHu\\OneDrive\\文档\\ZTE\\入职\\毕业生相关材料\\06.个人简历04.jpg",
                "C:\\Users\\KJHu\\OneDrive\\文档\\ZTE\\入职\\毕业生相关材料\\06.个人简历03.jpg",
                "C:\\Users\\KJHu\\OneDrive\\文档\\ZTE\\入职\\毕业生相关材料\\06.个人简历02.jpg","C:\\Users\\KJHu\\OneDrive\\文档\\ZTE\\入职\\毕业生相关材料\\（双面打印）应聘人员信息登记表-（中文版）20200724.pdf",
                "C:\\Users\\KJHu\\OneDrive\\文档\\ZTE\\入职\\毕业生相关材料\\06.个人简历05.jpg",
                "C:\\Users\\KJHu\\OneDrive\\文档\\ZTE\\入职\\毕业生相关材料\\06.个人简历04.jpg",
                "C:\\Users\\KJHu\\OneDrive\\文档\\ZTE\\入职\\毕业生相关材料\\06.个人简历03.jpg",
                "C:\\Users\\KJHu\\OneDrive\\文档\\ZTE\\入职\\毕业生相关材料\\06.个人简历02.jpg",
                "C:\\Users\\KJHu\\OneDrive\\文档\\ZTE\\入职\\毕业生相关材料\\06.个人简历01.jpg"
        };

        for (String f : args) {
            Runnable r = new DigestRunnable(f);
            new Thread(r).start();
        }
    }

}
