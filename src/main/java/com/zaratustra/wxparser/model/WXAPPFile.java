package com.zaratustra.wxparser.model;

/**
 * Created by zaratustra on 2017/10/24.
 */
public class WXAPPFile {

    private int mFileNameLength;
    private String mFileName;
    private int mFileStart;
    private int mFileSize;
    private byte[] mFileData;

    public int getFileNameLength() {
        return mFileNameLength;
    }

    public void setFileNameLength(int fileNameLength) {
        mFileNameLength = fileNameLength;
    }

    public String getFileName() {
        return mFileName;
    }

    public void setFileName(String fileName) {
        mFileName = fileName;
    }

    public int getFileStart() {
        return mFileStart;
    }

    public void setFileStart(int fileStart) {
        mFileStart = fileStart;
    }

    public int getFileSize() {
        return mFileSize;
    }

    public void setFileSize(int fileSize) {
        mFileSize = fileSize;
    }

    public byte[] getFileData() {
        return mFileData;
    }

    public void setFileData(byte[] fileData) {
        mFileData = fileData;
    }

}
