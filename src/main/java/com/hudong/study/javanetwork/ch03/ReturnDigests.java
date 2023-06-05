package com.hudong.study.javanetwork.ch03;

import javax.xml.bind.DatatypeConverter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ReturnDigests extends Thread {
    private String filename;
    private byte[] digest;

    public ReturnDigests(String filename) {
        this.filename = filename;
    }

    public byte[] getDigest() {
        return digest;
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
            digest = sha.digest();
//            StringBuilder result = new StringBuilder(filename);
//            result.append(": ");
//            result.append(DatatypeConverter.printHexBinary(digest));
//            System.out.println(result);
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

}
