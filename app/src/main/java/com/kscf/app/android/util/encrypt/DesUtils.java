package com.kscf.app.android.util.encrypt;

import android.util.Base64;

import java.io.IOException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DesUtils {

    private final static String DES = "DES";
    private final static String CHARSET = "UTF-8";
    private final static String DES_NoPadding = "DES";

    public static void main(String[] args) throws Exception {
        String data = "HE_CZ2016040917440530821373272";
        String key = "blue!@#$%";
        // String en = encrypt(data, key);
        // System.err.println(en);
        // System.err.println(decrypt(en, key));

        String enSimple = encrypt(data, key);
        System.out.println(enSimple);
        String deSimple = decrypt(enSimple, key);
        System.out.println(deSimple);


        // String s2 = encrypt2(data, key);
        // System.out.println(s2);
        // System.out.println(decrypt2(s2, key));

    }

    /**
     * Description 根据键值进行加密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    public static String encrypt2(String data, String key) throws Exception {
        byte[] bt = encrypt(data.getBytes(), key.getBytes());
        //String strs = new BASE64Encoder().encode(bt);
        //return strs;
        return new String(Base64.encode(bt, Base64.DEFAULT), CHARSET);
    }

    /**
     * Description 根据键值进行解密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String decrypt2(String data, String key) throws IOException, Exception {
        if (data == null)
            return null;
        //BASE64Decoder decoder = new BASE64Decoder();
        //byte[] buf = decoder.decodeBuffer(data);
        //byte[] bt = decrypt(buf, key.getBytes());
        //return new String(bt);
        byte[] buf = Base64.decode(data, Base64.DEFAULT);
        byte[] bt = decrypt(buf, key.getBytes());
        return new String(bt);
    }

    public static String encrypt(String data, String key) throws Exception {
        byte[] bt = encrypt(data.getBytes(), key.getBytes());
        //String strs = new BASE64Encoder().encode(bt);
        String strs = new String(Base64.encode(bt, Base64.DEFAULT), CHARSET);
        return strs.replace("/", "_").replace("+", "-");
    }

    public static String decrypt(String data, String key) throws IOException, Exception {
        if (data == null)
            return null;
        //BASE64Decoder decoder = new BASE64Decoder();
        //byte[] buf = decoder.decodeBuffer(data.replace("_", "/").replace("-", "+"));
        //byte[] bt = decrypt(buf, key.getBytes());
        //return new String(bt);
        byte[] buf = Base64.decode(data.replace("_", "/").replace("-", "+"), Base64.DEFAULT);
        byte[] bt = decrypt(buf, key.getBytes());
        return new String(bt);
    }

    private static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0)
            throw new IllegalArgumentException("长度不是偶数");
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }

    private static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toUpperCase();
    }

    /**
     * Description 根据键值进行加密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        // SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES_NoPadding);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey);

        return cipher.doFinal(data);
    }

    /**
     * Description 根据键值进行解密
     *
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        // SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES_NoPadding);

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey);

        return cipher.doFinal(data);
    }
}
