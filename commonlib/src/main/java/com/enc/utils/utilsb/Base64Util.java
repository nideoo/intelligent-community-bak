package com.enc.utils.utilsb;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.security.NoSuchAlgorithmException;

public class Base64Util {

    /**
     * BASE64解密
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }
    /**
     * BASE64加密
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    public static void main(String[] args) {
        String clearStr = "12yyyy34567890-=rtyuiodsfsdfsdfklmsfmsdflmsdafsdfsdfssssdfdsfsdfdsdfwerrwerpteoryym/,ppdksdfldfsdlkmmsdf6783$%#!~!#$%^&*&(FRANKfrank22411";
        String clearMd5 = null;
        try {
            clearMd5 = Md5Util.md5Hex(clearStr);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String cipherStr = null;
        try {
            cipherStr = encryptBASE64(clearMd5.getBytes());
        } catch (Exception e) {

        }
    }

}
