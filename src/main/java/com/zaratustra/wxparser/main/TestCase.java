package com.zaratustra.wxparser.main;

import java.io.IOException;

/**
 * Created by zaratustra on 2017/10/24.
 */
public class TestCase {

    public static void main(String[] args){
        WXAppParser wxAppParser = new WXAppParser();
        try {
            wxAppParser.unzipWXAppPackage("/Users/rosejames/Desktop/zarathustra/wxapp-parser/test.wxapkg", "/Users/rosejames/Desktop/zarathustra/wxapp-parser/test/");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidWXPackageException e) {
            e.printStackTrace();
        }
    }
}

