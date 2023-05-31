package com.hudong.study.javanetwork.ch02;

import java.io.*;
import java.nio.file.Files;

public class Write2OutputStream {

    public static void generateCharacters(OutputStream out) throws IOException {
        int firstPrintableCharacter = 33;
        int numberOfPrintableCharacters = 94;
        int numberOfCharacterPerLine = 72;

        int start = firstPrintableCharacter;
        while (true) {
            for (int i = start; i < start + numberOfCharacterPerLine; i++) {
                out.write(
                        ((i - firstPrintableCharacter) % numberOfPrintableCharacters)
                                + firstPrintableCharacter
                );

            }
            out.write('\r');
            out.write('\n');
            start = ((start + 1) - firstPrintableCharacter) % numberOfPrintableCharacters + firstPrintableCharacter;
        }
    }


    public static void main(String[] args) throws IOException {
        OutputStream stream = Files.newOutputStream(new File("test.txt").toPath());
        Write2OutputStream.generateCharacters(stream);
        stream.close();
    }
}
