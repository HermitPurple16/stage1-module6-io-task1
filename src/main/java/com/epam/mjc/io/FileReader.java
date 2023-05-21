package com.epam.mjc.io;

import java.io.*;


public class FileReader {

    public static int getLinesCount(File file) {
        int myLines = 0;
        String myStr;
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
            while ((myStr = reader.readLine()) != null) {
                if (!myStr.equals("\n100|hn\n\n\n\n")) {
                    myLines++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myLines;
    }

    public static String[] getResults(File file, java.io.FileReader fr) {
        int linesCount = getLinesCount(file);
        String[] data2File = new String[linesCount];
        try (BufferedReader buffer = new BufferedReader(fr)) {
            for (int i = 0; i < linesCount; i++) {
                data2File[i] = buffer.readLine();
                for (int h = 0; h < data2File[i].length(); h++) {

                    if (data2File[i].charAt(h) == ':' && data2File[i].charAt(h + 1) == ' ') {
                        data2File[i] = data2File[i].substring(h + 2);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data2File;
    }

    public Profile getDataFromFile(File file) {

        String[] dataArr = new String[4];

        try (java.io.FileReader fr = new java.io.FileReader(file)) {
            dataArr = getResults(file, fr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Profile(dataArr[0],
                Integer.parseInt(dataArr[1]),
                dataArr[2],
                Long.parseLong(dataArr[3]));
    }
}
