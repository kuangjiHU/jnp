package com.hudong.study.javanetwork.ch08;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Time {


    private static final String HOSTNAME = "localhost";

    public static Date getDateFromNetwork() throws IOException, ParseException {
        // 协议使用1900年
        // java 1970
        // 差额
//        long differenceBetweenEpochs = 2208898800L;
        TimeZone gmt = TimeZone.getTimeZone("GMT");
        Calendar epoch1900 = Calendar.getInstance(gmt);
        epoch1900.set(1900, 01, 01, 00, 00, 00);
        long epoch1900ms = epoch1900.getTime().getTime();
        Calendar epoch1970 = Calendar.getInstance(gmt);
        epoch1970.set(1970, 01, 01, 00, 00, 00);
        long epoch1970ms = epoch1970.getTime().getTime();
        long differenceInMs = epoch1970ms - epoch1900ms;
        long differenceBetweenEpochs = differenceInMs / 1000;
        try (Socket socket = new Socket(HOSTNAME, 37)) {
            socket.setSoTimeout(1500);
            InputStream in = socket.getInputStream();

            long secondsSince1900 = 0;
            for (int i = 0; i < 4; i++) {
                secondsSince1900 = (secondsSince1900 << 8) | in.read();
            }
            long secondsSince1970 = secondsSince1900 - differenceBetweenEpochs;

            Date date = new Date(secondsSince1970);
            return date;
        }
    }

    static Date parseDate(String s) throws ParseException {
        String[] pieces = s.split(" ");
        String dateTime = pieces[1] + " " + pieces[2] + " UTC";
        DateFormat format = new SimpleDateFormat("yy-MM-dd hh:mm:ss z");
        return format.parse(dateTime);
    }

    public static void main(String[] args) throws IOException, ParseException {
        System.out.println(getDateFromNetwork());
    }
}
