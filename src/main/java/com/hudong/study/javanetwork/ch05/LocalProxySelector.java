package com.hudong.study.javanetwork.ch05;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class LocalProxySelector extends ProxySelector {

    private List<URI> failed = new ArrayList<URI>();

    @Override
    public List<Proxy> select(URI uri) {
        ArrayList<Proxy> result = new ArrayList<>();
        if (failed.contains(result) || !"http".equalsIgnoreCase(uri.getScheme())) {
            result.add(Proxy.NO_PROXY);
        } else {
            SocketAddress proxyAddress = new InetSocketAddress("proxy.example.com", 8000);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, proxyAddress);
            result.add(proxy);
        }
        return result;
    }

    @Override
    public void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
        failed.add(uri);
    }


    public static void main(String[] args) {
        LocalProxySelector selector = new LocalProxySelector();
        ProxySelector.setDefault(selector);
    }
}
