package com.zaratustra.wxparser.utils;


/**
 * Created by zaratustra on 2017/10/24.
 */
public class ByteUtils {

    /**
     * 将四位bytes转换成int
     *
     * @param src
     * @return
     */
    public static int fourBytesToInt(byte[] src) {
        return ((src[3] & 0xFF)
                | ((src[2] & 0xFF) << 8)
                | ((src[1] & 0xFF) << 16)
                | ((src[0] & 0xFF) << 24));
    }

    /**
     * bytes转换成十六进制字符串
     *
     * @param b byte[] byte数组
     * @return String 每个Byte值之间空格分隔
     */
    public static String byte2HexStr(byte[] b) {
        char[] CHARS = "0123456789ABCDEF".toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int n = 0; n < b.length; n++) {
            sb.append(CHARS[(b[n] & 0xFF) >> 4]);
            sb.append(CHARS[b[n] & 0x0F]);
        }
        return sb.insert(0, "0x").toString();
    }

}
