package com.epam.mjc.io;

import java.io.*;


public class FileReader {

    public static int getLinesCount(File file) {
        int linesCount = 0;

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
            String noNullStr = null;
            while ((noNullStr = reader.readLine()) != null) {
                linesCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linesCount;
    }

    public Profile getDataFromFile(File file) {

        String[] dataArr = new String[4];
        java.io.FileReader fr = null;
        BufferedReader buffer = null;
        try {
            fr = new java.io.FileReader(file);
            buffer = new BufferedReader(fr);

            int linesCount = getLinesCount(file);

            String[] data2File = new String[linesCount];
            for (int i = 0; i < linesCount; i++) {
                data2File[i] = buffer.readLine();
                for (int h = 0; h < data2File[i].length(); h++) {

                    if (data2File[i].charAt(h) == ':' && data2File[i].charAt(h + 1) == ' ') {
                        data2File[i] = data2File[i].substring(h + 2);
                        break;
                    }
                }
            }
            dataArr = data2File;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fr.close();
                buffer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new Profile(dataArr[0],
                Integer.parseInt(dataArr[1]),
                dataArr[2],
                Long.parseLong(dataArr[3]));
    }
}
