package com.hudong.study.javanetwork.ch03;

import javax.xml.bind.DatatypeConverter;

public class InstanceCallbackDigestUserInterface {

    private String filename;
    private byte[] digest;

    public InstanceCallbackDigestUserInterface(String filename) {
        this.filename = filename;
    }

    public void calculateDigest() {
        InstanceCallbackDigest cb = new InstanceCallbackDigest(filename, this);
        new Thread(cb).start();
    }

    void receiveDigest(byte[] digest) {
        this.digest = digest;
    }


    public String toString() {
        StringBuilder result = new StringBuilder(filename);
        result.append(": ");
        if (digest != null) {
            result.append(DatatypeConverter.printHexBinary(digest));
        } else {
            result.append("digest not available!");
        }
        return result.toString();
    }

    public static void main(String[] args) {
        args = new String[]{
                "C:\\Users\\KJHu\\OneDrive\\文档\\ZTE\\入职\\毕业生相关材料\\（双面打印）应聘人员信息登记表-（中文版）20200724.pdf",
                "C:\\Users\\KJHu\\OneDrive\\文档\\ZTE\\入职\\毕业生相关材料\\06.个人简历05.jpg",
                "C:\\Users\\KJHu\\OneDrive\\文档\\ZTE\\入职\\毕业生相关材料\\06.个人简历04.jpg",
                "C:\\Users\\KJHu\\OneDrive\\文档\\ZTE\\入职\\毕业生相关材料\\06.个人简历03.jpg",
                "C:\\Users\\KJHu\\OneDrive\\文档\\ZTE\\入职\\毕业生相关材料\\06.个人简历02.jpg",
                "C:\\Users\\KJHu\\OneDrive\\文档\\ZTE\\入职\\毕业生相关材料\\06.个人简历01.jpg"
        };
        for (int i = 0; i < args.length; i++) {
            String f = args[i];
            InstanceCallbackDigestUserInterface d = new InstanceCallbackDigestUserInterface(f);
            d.calculateDigest();
        }
    }
}
