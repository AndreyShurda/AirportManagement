package com.andrey.main.ui;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class EncryptionDecryptionAES {
    static Cipher cipher;
    static SecretKey secretKey;
    static Cipher cipherRSA;
    static PublicKey publicKey;
    static PrivateKey privateKey;

    static {
        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128); // block size is 128bits
            secretKey = keyGenerator.generateKey();
            cipher = Cipher.getInstance("AES");



            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(1024);
            KeyPair kp = kpg.generateKeyPair();
            publicKey = kp.getPublic();
            privateKey = kp.getPrivate();
            cipherRSA = Cipher.getInstance("RSA");

            System.out.println("publicKey: "+publicKey);
            System.out.println("privateKey: "+privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws Exception {
        /*
         create key
         If we need to generate a new key use a KeyGenerator
         If we have existing plaintext key use a SecretKeyFactory
        */


        /*
          Cipher Info
          Algorithm : for the encryption of electronic data
          mode of operation : to avoid repeated blocks encryptAES to the same values.
          padding: ensuring messages are the proper length necessary for certain ciphers
          mode/padding are not used with stream cyphers.
         */
         //SunJCE provider AES algorithm, mode(optional) and padding schema(optional)

        String plainText = "AES Symmetric Encryption Decryption";
        System.out.println("Plain Text Before Encryption: " + plainText);

        String encryptedText = encryptAES(plainText);
        System.out.println("Encrypted Text After Encryption: " + encryptedText);

        String decryptedText = decryptAES(encryptedText);
        System.out.println("Decrypted Text After Decryption: " + decryptedText);

        String encryptedTextRSA = encryptAES(plainText);
        System.out.println("Encrypted Text After EncryptionRSA: " + encryptedTextRSA);

        String decryptedTextRSA = decryptAES(encryptedText);
        System.out.println("Decrypted Text After DecryptionRSA: " + decryptedTextRSA);



    }

    public static String encryptAES(String plainText) throws Exception {
        byte[] plainTextByte = plainText.getBytes();
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedByte = cipher.doFinal(plainTextByte);
        Base64.Encoder encoder = Base64.getEncoder();
        String encryptedText = encoder.encodeToString(encryptedByte);
        return encryptedText;
    }

    public static String decryptAES(String encryptedText) throws Exception {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] encryptedTextByte = decoder.decode(encryptedText);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
        String decryptedText = new String(decryptedByte);
        return decryptedText;
    }

    public static String encryptRSA(String plainText) throws Exception {
        byte[] plainTextByte = plainText.getBytes();
        cipherRSA.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedByte = cipherRSA.doFinal(plainTextByte);
        Base64.Encoder encoder = Base64.getEncoder();
        String encryptedText = encoder.encodeToString(encryptedByte);
        return encryptedText;
    }

    public static String decryptRSA(String encryptedText) throws Exception {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] encryptedTextByte = decoder.decode(encryptedText);
        cipherRSA.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
        String decryptedText = new String(decryptedByte);
        return decryptedText;
    }
}