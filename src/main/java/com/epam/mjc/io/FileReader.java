package com.epam.mjc.io;

import java.io.*;


public class FileReader {

    public Profile getDataFromFile(File file) {

        String[] dataArr = new String[4];

        try {
            java.io.FileReader fr = new java.io.FileReader(file);
            BufferedReader buffer = new BufferedReader(fr);

            int linesCount = 0;

            try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
                while (reader.readLine() != null) linesCount++;
            } catch (IOException e) {
                e.printStackTrace();
            }

            String[] data2File = new String[linesCount];
            for (int i = 0; i < linesCount; i++) {
                data2File[i] = buffer.readLine();
                for (int h = 0; h < data2File[i].length(); h++) {

                    if (data2File[i].charAt(h) == ':' && data2File[i].charAt(h + 1) == ' ') {
                        data2File[i] = data2File[i].substring(h + 2);
                        h = data2File[i].length() + 1;
                    }
                }
            }
            dataArr = data2File;
            fr.close();
            buffer.close();
        } catch (IOException e) {
            System.out.println("IOException");
        }
        return new Profile(dataArr[0],
                Integer.parseInt(dataArr[1]),
                dataArr[2],
                Long.parseLong(dataArr[3]));
    }
}
