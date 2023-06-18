package com.hudong.study.javanetwork.ch07;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Locale;

public class CacheControl {

    private Date maxAge = null;
    private Date sMaxAge = null;
    private boolean mustRevalidate = false;
    private boolean noCache = false;
    private boolean noStore = false;
    private boolean proxyRevalidate = false;
    private boolean publicCache = false;
    private boolean privateCache = false;

    public Date getMaxAge() {
        return maxAge;
    }

    public Date getsMaxAge() {
        return sMaxAge;
    }

    public boolean isMustRevalidate() {
        return mustRevalidate;
    }

    public boolean isNoCache() {
        return noCache;
    }

    public boolean isNoStore() {
        return noStore;
    }

    public boolean isProxyRevalidate() {
        return proxyRevalidate;
    }

    public boolean isPublicCache() {
        return publicCache;
    }

    public boolean isPrivateCache() {
        return privateCache;
    }

    @Override
    public String toString() {
        return "CacheControl{" +
                "maxAge=" + maxAge +
                ", sMaxAge=" + sMaxAge +
                ", mustRevalidate=" + mustRevalidate +
                ", noCache=" + noCache +
                ", noStore=" + noStore +
                ", proxyRevalidate=" + proxyRevalidate +
                ", publicCache=" + publicCache +
                ", privateCache=" + privateCache +
                '}';
    }

    public CacheControl(String s) {
        if (s == null || !s.contains(":")) {
            return; // 默认策略
        }
        String value = s.split(":")[1].trim();
        String[] components = value.split(",");
        Date now = new Date();

        for (String component :
                components) {
            component = component.trim().toLowerCase(Locale.US);
            if (component.startsWith("max-age=")) {
                int secondsInTheFuture = Integer.parseInt(component.substring(8));
                maxAge = new Date(now.getTime() + 1000L * secondsInTheFuture);
            } else if (component.startsWith("s-maxage=")) {
                int secondsInTheFuture = Integer.parseInt(component.substring(8));
                sMaxAge = new Date(now.getTime() + 1000L * secondsInTheFuture);
            } else if (component.equals("must-revalidate")) {
                mustRevalidate = true;
            } else if (component.equals("proxy=revalidate")) {
                proxyRevalidate = true;
            } else if (component.equals("no-cache")) {
                noCache = true;
            } else if (component.equals("public")) {
                publicCache = true;
            } else if (component.equals("private")) {
                privateCache = true;
            }
        }


    }

    public static void main(String[] args) {
        try {
            URL url = new URL("https://www.bilibili.com/");
            URLConnection uc = url.openConnection();
            System.out.println(uc.getHeaderFields());
            if(uc.getHeaderFields().containsKey("Cache-Control")){
                String field = uc.getHeaderField("Cache-Control");
                System.out.println(field);
                CacheControl control = new CacheControl(field);

                System.out.println(control);
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
