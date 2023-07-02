package com.hudong.study.javanetwork.ch10;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Arrays;

public class SecureOrderTaker {
    public static final int PORT = 7000;
    public static final String algorithm = "SSL";


    public static void main(String[] args) {
        try {
            SSLContext context = SSLContext.getInstance(algorithm);
            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
            KeyStore ks = KeyStore.getInstance("JKS");
            char[] password = System.console().readPassword();
            ks.load(new FileInputStream("jnp4e.keys"), password);
            kmf.init(ks, password);
            context.init(kmf.getKeyManagers(), null, null);

            Arrays.fill(password, '0');
            SSLServerSocketFactory factory = context.getServerSocketFactory();
            SSLServerSocket server = (SSLServerSocket) factory.createServerSocket(PORT);
            String[] supported = server.getSupportedCipherSuites();
            String[] anonCipherSuitesSupported = new String[supported.length];
            int numAnonCipherSuitesSupported = 0;
            for (int i = 0; i < supported.length; i++) {
                if (supported[i].indexOf("_anon_") > 0) {
                    anonCipherSuitesSupported[numAnonCipherSuitesSupported++] = supported[i];
                }
            }

            String[] oldEnabled = server.getEnabledCipherSuites();
            String[] newEnabled = new String[oldEnabled.length + numAnonCipherSuitesSupported];
            System.arraycopy(oldEnabled, 0, newEnabled, 0, oldEnabled.length);
            System.arraycopy(supported, 0, newEnabled, oldEnabled.length, numAnonCipherSuitesSupported);
            server.setEnabledCipherSuites(newEnabled);
            while (true){
                try(Socket theConnection = server.accept();){
                    InputStream in = theConnection.getInputStream();
                    int c ;
                    while ((c= in.read())!=-1){
                        System.out.write(c);
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }

        } catch (NoSuchAlgorithmException | KeyStoreException | IOException | CertificateException |
                 UnrecoverableKeyException | KeyManagementException e) {
            e.printStackTrace();
        }
    }
}
