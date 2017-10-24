package com.zaratustra.wxparser.main;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by zaratustra on 2017/10/24.
 */
public class FileUtils {

    private static final int BUFFER_SIZE = 1024;

    public static boolean makeDirs(String filePath) {
        File file = new File(filePath);
        String folderName = file.getParent();
        if (folderName == null || folderName.isEmpty()) {
            return false;
        }

        File folder = new File(folderName);
        return (folder.exists() && folder.isDirectory()) ? true : folder.mkdirs();
    }

    public static void saveFile(String inputPath, int fileStart, int fileSize, String fileName) throws IOException {
        FileInputStream inputStream = new FileInputStream(inputPath);
        FileOutputStream outputStream = new FileOutputStream(fileName);
        try {
            int currentLength = 0;
            byte[] buffer = new byte[BUFFER_SIZE];
            inputStream.skip(fileStart);

            int readLength;
            while ((readLength = inputStream.read(buffer)) > 0) {
                currentLength += readLength;
                if (currentLength >= fileSize) {
                    int more = currentLength - fileSize;
                    outputStream.write(Arrays.copyOfRange(buffer, 0, BUFFER_SIZE - more));
                    break;
                }
                outputStream.write(buffer);
            }
        } finally {
            try {
                inputStream.close();
            } catch (Exception e) {

            }
            try {
                outputStream.close();
            } catch (Exception e) {

            }
        }
    }
}
