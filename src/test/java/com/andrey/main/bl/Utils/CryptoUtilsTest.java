package com.andrey.main.bl.Utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by admin on 13.07.2017.
 */
public class CryptoUtilsTest {

    @Test
    public void encode() throws Exception {
        String ActualStr = "Abc123";
        String ExpectedStr = "QWJjMTIz\r\n";
        String encode = CryptoUtils.encode(ActualStr);
//        String ExpectedStr = CryptoUtils.decode(encode);

//        assertTrue(ActualStr.equals(ExpectedStr));
        assertTrue(encode.equals(ExpectedStr));
    }

    @Test
    public void decode() throws Exception {
        String ActualStr = "Abc123";
        String encode = CryptoUtils.encode(ActualStr);
        String ExpectedStr = CryptoUtils.decode(encode);

        assertTrue(ActualStr.equals(ExpectedStr));

    }

}