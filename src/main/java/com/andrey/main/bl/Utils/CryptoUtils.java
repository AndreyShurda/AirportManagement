package com.andrey.main.bl.Utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import java.io.IOException;


public class CryptoUtils {
    public static String encode(String str) {
        BASE64Encoder encoder = new BASE64Encoder();
        str = new String(encoder.encodeBuffer(str.getBytes()));
        return str;
    }

    public static String decode(String str) {
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            str = new String(decoder.decodeBuffer(str));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
