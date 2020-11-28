package com.enc.utils.utilsb;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;

/**
 * @Description: AES加密工具
 * @author Augus
 * @date 2018/7/6 18:21
 */
// @Slf4j
public class AesUtil {

    public static final String ALGORITHM = "AES";

    /**
     * 转换密钥<br>
     * @param key
     * @return
     * @throws Exception
     */
    private static Key toKey(byte[] key) {
        SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);
        return secretKey;
    }

    /**
     * 解密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] data, String key) throws Exception {
        try {
            Key k = toKey(Base64Util.decryptBASE64(key));
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, k);
            return cipher.doFinal(data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 加密
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] data, String key) throws Exception {
        Key k = toKey(Base64Util.decryptBASE64(key));
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    /**
     * 生成密钥
     * @return
     * @throws Exception
     */
    public static String initKey() throws Exception {
        return initKey(null);
    }

    /**
     * 生成密钥
     * @param seed
     * @return
     * @throws Exception
     */
    public static String initKey(String seed) throws Exception {
        SecureRandom secureRandom = null;

        try {

//            if (seed != null) {
//                secureRandom = new SecureRandom(Base64Util.decryptBASE64(seed));
//            } else {
//                secureRandom = new SecureRandom();
//            }
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(Base64Util.decryptBASE64(seed));

            KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);
           // kg.init(128,secureRandom);
            kg.init(128, random);


            SecretKey secretKey = kg.generateKey();
            return Base64Util.encryptBASE64(secretKey.getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        String inputStr = "AES1234567890-·！@#￥%……&*（）——+~！@#￥%……&*（）WQERTYUIOASDFGHJKL：“ZXCVBNM《》？";
        String key = initKey();
        System.err.println("原文:\t" + inputStr);

        System.err.println("密钥:\t" + key);

        byte[] inputData = inputStr.getBytes();
        inputData = encrypt(inputData, key);

        System.err.println("加密后:\t" + Base64Util.encryptBASE64(inputData));

        byte[] outputData = decrypt(inputData, key);
        String outputStr = new String(outputData);

        System.err.println("解密后:\t" + outputStr);

    }

}
