package com.hudong.study.javanetwork.ch08;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DayTime {
    public static Date getDateFromNetwork() throws IOException, ParseException {
        String hostname = "time.nist.gov";
        try (Socket socket = new Socket(hostname, 13)) {
            socket.setSoTimeout(15000);
            InputStream in = socket.getInputStream();
            StringBuilder time = new StringBuilder();
            InputStreamReader reader = new InputStreamReader(in, StandardCharsets.US_ASCII);
            for (int c = reader.read(); c != -1; c = reader.read()) {
                time.append((char) c);
            }
            return parseDate(time.toString());
        }
    }

    static Date parseDate(String s) throws ParseException {
        String[] pieces = s.split(" ");
        String dateTime = pieces[1] + " " + pieces[2] + " UTC";
        DateFormat format = new SimpleDateFormat("yy-MM-dd hh:mm:ss z");
        return format.parse(dateTime);
    }


    public static void main(String[] args) throws IOException, ParseException {
        Date date = getDateFromNetwork();
        System.out.println(date);
    }
}
