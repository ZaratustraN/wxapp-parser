package com.zaratustra.wxparser.main;

import com.zaratustra.wxparser.model.WXAPPFile;
import com.zaratustra.wxparser.model.WXAPPPackage;
import com.zaratustra.wxparser.utils.ByteUtils;
import com.zaratustra.wxparser.utils.FileUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by zaratustra on 2017/10/24.
 */
public class WXAppParser {

    public void unzipWXAppPackage(String inputPath, String outputPath) throws IOException, InvalidWXPackageException {
        WXAPPPackage wxappPackage = parse(inputPath);

        for (int i = 0; i < wxappPackage.getFiles().size(); i++) {
            WXAPPFile wxappFile = wxappPackage.getFiles().get(i);
            FileUtils.makeDirs(outputPath + wxappFile.getFileName());
            FileUtils.saveFile(inputPath, wxappFile.getFileStart(), wxappFile.getFileSize(), outputPath + wxappFile.getFileName());
        }
    }

    public WXAPPPackage parse(String path) throws IOException, InvalidWXPackageException {
        FileInputStream fileInputStream = new FileInputStream(path);

        fileInputStream.skip(1);

        int edition = getEdition(fileInputStream);
        System.out.println("Edition: " + edition);

        int indexLength = getIndexLength(fileInputStream);
        System.out.println("Index Length: " + indexLength);

        int bodyLength = getBodyLength(fileInputStream);
        System.out.println("Body Length: " + bodyLength);

        fileInputStream.skip(1);

        int fileCount = getFileCount(fileInputStream);
        System.out.println("File Count: " + fileCount);

        ArrayList<WXAPPFile> wxappFiles = new ArrayList<>();


        for (int i = 0; i < fileCount; i++) {
            int fileNameLength = getFileNameLength(fileInputStream);
            String fileName = getFileName(fileInputStream, fileNameLength);
            int fileOffset = getFileOffset(fileInputStream);
            int fileSize = getFileSize(fileInputStream);
            System.out.println("File Name: " + fileName + ", File offset: " + fileOffset + ", File size: " + fileSize);
            WXAPPFile file = new WXAPPFile();
            file.setFileNameLength(fileNameLength);
            file.setFileName(fileName);
            file.setFileSize(fileSize);
            file.setFileStart(fileOffset);
            wxappFiles.add(file);
        }


        WXAPPPackage wxappPackage = new WXAPPPackage();
        wxappPackage.setEdition(edition);
        wxappPackage.setIndexLength(indexLength);
        wxappPackage.setBodyLength(bodyLength);
        wxappPackage.setFileCount(fileCount);
        wxappPackage.setFiles(wxappFiles);

        return wxappPackage;
    }


    private int getEdition(FileInputStream fileInputStream) throws IOException {
        byte[] editionBytes = new byte[4];
        fileInputStream.read(editionBytes, 0, 4);
        return ByteUtils.fourBytesToInt(editionBytes);
    }

    private int getIndexLength(FileInputStream fileInputStream) throws IOException {
        byte[] indexLengthBytes = new byte[4];
        fileInputStream.read(indexLengthBytes, 0, 4);
        return ByteUtils.fourBytesToInt(indexLengthBytes);
    }

    private int getBodyLength(FileInputStream fileInputStream) throws IOException {
        byte[] bodyLengthBytes = new byte[4];
        fileInputStream.read(bodyLengthBytes, 0, 4);
        return ByteUtils.fourBytesToInt(bodyLengthBytes);
    }

    private int getFileCount(FileInputStream fileInputStream) throws IOException {
        byte[] fileCountBytes = new byte[4];
        fileInputStream.read(fileCountBytes, 0, 4);
        return ByteUtils.fourBytesToInt(fileCountBytes);
    }

    private int getFileNameLength(FileInputStream fileInputStream) throws IOException {
        byte[] fileNameLengthBytes = new byte[4];
        fileInputStream.read(fileNameLengthBytes, 0, 4);
        return ByteUtils.fourBytesToInt(fileNameLengthBytes);
    }

    private String getFileName(FileInputStream fileInputStream, int fileNameLength) throws IOException {
        byte[] fileNameBytes = new byte[fileNameLength];
        fileInputStream.read(fileNameBytes, 0, fileNameLength);
        return new String(fileNameBytes, "utf-8");
    }

    private int getFileOffset(FileInputStream fileInputStream) throws IOException {
        byte[] fileOffsetBytes = new byte[4];
        fileInputStream.read(fileOffsetBytes, 0, 4);
        return ByteUtils.fourBytesToInt(fileOffsetBytes);
    }

    private int getFileSize(FileInputStream fileInputStream) throws IOException {
        byte[] fileSizeBytes = new byte[4];
        fileInputStream.read(fileSizeBytes, 0, 4);
        return ByteUtils.fourBytesToInt(fileSizeBytes);
    }


}
