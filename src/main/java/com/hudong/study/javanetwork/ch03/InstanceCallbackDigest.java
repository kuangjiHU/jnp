package com.hudong.study.javanetwork.ch03;

import javax.xml.bind.DatatypeConverter;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class InstanceCallbackDigest implements Runnable {

    private String filename;
    private InstanceCallbackDigestUserInterface callback;

    public InstanceCallbackDigest(String filename, InstanceCallbackDigestUserInterface callback) {
        this.filename = filename;
        this.callback = callback;
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
            callback.receiveDigest(digest);
            System.out.println(callback);
        } catch (IOException e) {
            System.err.println(e);
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e);
        }

    }
}
