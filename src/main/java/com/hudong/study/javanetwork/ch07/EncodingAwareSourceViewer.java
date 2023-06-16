package com.hudong.study.javanetwork.ch07;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class EncodingAwareSourceViewer {

    public static void main(String[] args) {
        args = new String[]{
                "https://data.bilibili.com/log/web?0133241686845222846https%3A%2F%2Fwww.bilibili.com%2F|333.1007.home.feed.page_hang_time||1686845222846|||430x762|1|{%22counting_seconds%22:1,%22b_nut_h%22:1633593600,%22lsid%22:%223A36D4D4_188BFCEC41D%22,%22buvid_fp%22:%227cd6ed63328cc5a382043439d98d5d3e%22,%22buvid4%22:%228EF95C16-76D0-5EAF-D298-74EA0B4F051C60846-022012700-DEg2JXDtVgDlK%2FVqTx7EDfIH6fWeoFuZdyQyq2%2BSGkEMzmffH%2ByZDg%3D%3D%22,%22bsource_origin%22:%22empty%22,%22share_source_origin%22:%22empty%22}|{%22b_ut%22:%225%22,%22home_version%22:%22V8%22,%22i-wanna-go-back%22:%22-1%22,%22in_new_ab%22:true,%22ab_version%22:{%22for_ai_home_version%22:%22V8%22,%22tianma_banner_inline%22:%22CONTROL%22,%22login_dialog_version%22:%22V0%22,%22h5_read_awaken_app%22:%22B%22,%22home_pop_window%22:%22V1%22,%22channel_show_back_btn%22:%22HIDDEN%22,%22nano_pcdn_version%22:%22V_PCDN%22,%22in_theme_version%22:%22CLOSE%22,%22storage_back_btn%22:%22HIDE%22,%22web_homepage_video_continuation%22:%22CLOSE%22,%22clean_version_old%22:%22KEEP%22},%22ab_split_num%22:{%22for_ai_home_version%22:50,%22tianma_banner_inline%22:50,%22login_dialog_version%22:28,%22h5_read_awaken_app%22:12,%22home_pop_window%22:37,%22channel_show_back_btn%22:99,%22nano_pcdn_version%22:71,%22in_theme_version%22:32,%22storage_back_btn%22:5,%22web_homepage_video_continuation%22:5,%22clean_version_old%22:51}}||E749710A6-71023-110A3-1128-1D210C966B25358627infoc|zh-CN|null|1"
        };
        try {
            String encoding = "ISO-8859-1";
            URL url = new URL(args[0]);
            URLConnection uc = url.openConnection();
            String contentType = uc.getContentType();
            int encodingStart = contentType.indexOf("charset=");
            if(encodingStart!=-1){
                encoding = contentType.substring(encodingStart+8);
            }
            System.out.println(uc.getHeaderFields());
            try (InputStream raw = uc.getInputStream()) {
                InputStream buffer = new BufferedInputStream(raw);
                Reader reader = new InputStreamReader(buffer, encoding);
                int c;
                while ((c = reader.read()) != -1) {
                    System.out.print((char) c);
                }
            } catch (IOException e) {
                System.err.println(args[0] + " is not a parseable URL");
            }
        } catch (MalformedURLException e) {
            System.err.println(args[0] + " is not a parseable URL");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
