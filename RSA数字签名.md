
数字签名手册
============

一. 概述
-------

* 原理及特点
```
每个人都有一对「钥匙」（数字身份），其中一个只有她/他本人知道（密钥），另一个公开的（公钥）。
签名的时候用私钥，验证签名的时候用公钥。又因为任何人都可以落款申称她/他就是你，因此公钥必须向
接受者信任的人（身份认证机构）来注册。注册后身份认证机构给你发一数字证书。对文件签名后，你把此
数字证书连同文件及签名一起发给接受者，接受者向身份认证机构求证是否真地是用你的密钥签发的文件。
`
在通讯中使用数字签名一般基于以下原因：
```
1. 完整性
```
传输数据的双方都总希望确认消息未在传输的过程中被修改。加密使得第三方想要读取数据十分困难，
然而第三方仍然能采取可行的方法在传输的过程中修改数据。一个通俗的例子就是同形攻击：回想一下，
还是上面的那家银行从它的分行向它的中央管理系统发送格式为(a,b)的指令，其中a是账号，而b是账
户中的金额。一个远程客户可以先存100元，然后拦截传输结果，再传输(a,b3)，这样他就立刻变成百万富翁了。
```

2. 鉴权
```
    公钥加密系统允许任何人在发送信息时使用私钥进行加密，数字签名能够让信息接收者利用发送者的公钥确认发送者的身份。
当然，接收者不可能百分之百确信发送者的真实身份，而只能在密码系统未被破译的情况下才有理由确信。
`
    鉴权的重要性在财务数据上表现得尤为突出。举个例子，假设一家银行将指令由它的分行传输到它的中央管理系统，指令的格
式是(a,b)，其中a是账户的账号，而b是账户的现有金额。这时一位远程客户可以先存入100元，观察传输的结果，然后接二
连三的发送格式为(a,b)的指令。这种方法被称作重放攻击。
```

* 不可否认性
```
在密文背景下，抵赖这个词指的是不承认与消息有关的举动（即声称消息来自第三方）。消息的接收方可以通过数字签名来防止
所有后续的抵赖行为，因为接收方可以出示签名给别人看来证明信息的来源。
```

二. 平台实现
-------

* 渠道请求平台
```
渠道请求平台数据需要签名，然后综合接入平台需要使用渠道提供的公钥进行解密，确认
```

* 平台推送交易结果
```
综合接入平台会对推送的交易结果进行签名，推送给各个渠道，各个渠道在使用综合接入平台提供的公钥进行解密确认。
```

三. 数字签名的生成
-------

* 生成自签证书
```
下面产生一个自签证书。安装完J2SDK后，在J2SDK安装目录的bin目录下，有一个keytool的可执行程序。
利用keytool产生自签证书的步骤如下：
`
第一步，生成私钥
-------------
执行命令：
keytool -genkey -dname "cn=[凯石]',ou=[凯石],o=[凯石],l=[上海],s=[上海],c=[中国]" -keyalg RSA
-keysize 1024 -sigalg MD5withRSA –alias [zhangzhongcai] –keypass [123456]
–keystore  [d://14_private] –storepass [123456] –validity [10000]
[]括号内是渠道自行设置，生成的密钥文件是d://14_private
注意事项：
keypass和storepass密码设置一致
签名过程需要 alias和 storepass
`
第二步，生成公钥
--------------
执行命令：
keytool  -export -rfc –alias [zhangzhongcai]
-file [d://14_public] –keystore [d://14_private] – storepass  [123456]
[]括号内是渠道自行设置，生成的公钥文件是d://14_public
注意事项：
alias要和上面生成的私钥的代理名对应一致
keystore 要使用上面生成的私钥文件
storepass  要使用上面生成私钥的密码
```

四．数字签名，验证例子
-------

1. java验证例子

```java

package io.hefuyi.listener;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class TestDigit {

    /**
     * @param sigText
     * @param KeyPassword  keystore密码
     * @param KeyStorePath 公私密钥对，keystore是用来存放管理密钥对和证书链的
     * @param alias        表示使用这对公私密钥产生新的keystore入口的别名
     * @return
     */
    public static String sig(byte[] sigText, String
            KeyPassword, String KeyStorePath, String alias) {
        char[] kpass;
        int i;
        String encodeStr = null;
        try {
            KeyStore ks = KeyStore.getInstance("JKS");
            FileInputStream ksfis = new FileInputStream(KeyStorePath);
            BufferedInputStream ksbufin = new BufferedInputStream(ksfis);
            kpass = new char[KeyPassword.length()];
            for (i = 0; i < KeyPassword.length(); i++)
                kpass[i] = KeyPassword.charAt(i);
            ks.load(ksbufin, kpass);

            PrivateKey priv = (PrivateKey) ks.getKey(alias, kpass);
            Signature rsa = Signature.getInstance("MD5withRSA");
            rsa.initSign(priv);
            rsa.update(sigText);
            byte[] sig = rsa.sign();
            sun.misc.BASE64Encoder base64 = new sun.misc.BASE64Encoder();
            encodeStr = base64.encode(sig);
            System.out.println("sig is done " + encodeStr);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return encodeStr;
    }

    /**
     * @param updateData
     * @param sigedText
     */
    public static void veriSig(byte[] updateData, byte[] sigedText) {
        try {
            CertificateFactory
                    certificatefactory = CertificateFactory.getInstance("X.509");

            FileInputStream fin = new FileInputStream("e://14_public.cer");//公钥路径
            X509Certificate
                    certificate = (X509Certificate) certificatefactory.generateCertificate(fin);
            PublicKey pub = certificate.getPublicKey();
            Signature rsa = Signature.getInstance("MD5withRSA");
            rsa.initVerify(pub);
            rsa.update(updateData);

            boolean verifies = rsa.verify(sigedText);
            System.out.println("verified " + verifies);
            if (verifies) {
                System.out.println("Verify is done!");
            } else {
                System.out.println("verify is not successful");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
//		String encodeStr = "fHvkfVPzuZgE+0XMH6qD1D9FgNrUL3XL4x8ZbOOoipLd03VvF06ZLZ4h/vFkeB4iF0Jn1h8+S5OV211LFlXJrw08DqmhH1Pp4J4SOaVgs6K0z/adRVUj5jfDoJOG0+mE9LdhIs9g62TUzVKtFpMKCratJZjdMuH3tbgzNqKXzRE=";

//		String  encodeStr="I2Q5IKEL5HaF/N4tVBbO+GaOkPShnblKP70HjoLhglTbtXBSx69fW/xWj27BEBloCkTlXOPwYN+vEdk+UvQfge90ACkXGtjG75o5Knx2fopgHEHcsMhbIB4tjbyvKyaQcJiFX4FougMvyhjVWljIpU8A6KzHMJRRFFE7jFWTTUQ=";

        String sss2 = "000<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<msg><head transcode=\"000\" partnerid=\"14\" version=\"1.0\" time=\"2011041101802169\"/><body><msg errorCode=\"9015\" msg=\"订单不存在\"/></body></msg>";
        String siger = null;

        siger = TestDigit.sig(sss2.getBytes(), "111111", "e://14_private", "500wan");

        byte[] sigedText = null;
        sun.misc.BASE64Decoder base64 = new sun.misc.BASE64Decoder();
        try {
            sigedText = base64.decodeBuffer(siger);
        } catch (IOException e) {
            e.printStackTrace();
        }
//		System.out.println(encodeStr);
        System.out.println("siger------" + siger);
        TestDigit.veriSig(sss2.getBytes(), sigedText);


    }

}

```


2. android验证例子
```
import android.util.Base64;

import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

public class Rsa {
    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    private static final String PUBLIC_KEY = "RSAPublicKey";
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    public static byte[] decryptBASE64(String key) throws Exception {
        return Base64.decode(key, Base64.DEFAULT);
    }

    public static String encryptBASE64(byte[] key) throws Exception {
        return Base64.encodeToString(key, Base64.DEFAULT);
    }

    /**
     * 用私钥对信息生成数字签名
     *
     * @param data
     *            加密数据
     * @param privateKey
     *            私钥
     *
     * @return
     * @throws Exception
     */
    public static String sign(byte[] data, String privateKey) throws Exception {
        // 解密由base64编码的私钥
        byte[] keyBytes = decryptBASE64(privateKey);

        // 构造PKCS8EncodedKeySpec对象
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);

        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        // 取私钥匙对象
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 用私钥对信息生成数字签名
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(priKey);
        signature.update(data);

        return encryptBASE64(signature.sign());
    }

    /**
     * 校验数字签名
     *
     * @param data
     *            加密数据
     * @param publicKey
     *            公钥
     * @param sign
     *            数字签名
     *
     * @return 校验成功返回true 失败返回false
     * @throws Exception
     *
     */
    public static boolean verify(byte[] data, String publicKey, String sign)
            throws Exception {

        // 解密由base64编码的公钥
        byte[] keyBytes = decryptBASE64(publicKey);

        // 构造X509EncodedKeySpec对象
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

        // KEY_ALGORITHM 指定的加密算法
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

        // 取公钥匙对象
        PublicKey pubKey = keyFactory.generatePublic(keySpec);

        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(pubKey);
        signature.update(data);

        // 验证签名是否正常
        return signature.verify(decryptBASE64(sign));
    }

    /**
     * 解密<br>
     * 用私钥解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPrivateKey(byte[] data, String key)
            throws Exception {
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);

        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return cipher.doFinal(data);
    }

    /**
     * 解密<br>
     * 用公钥解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptByPublicKey(byte[] data, String key)
            throws Exception {
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);

        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);

        // 对数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        return cipher.doFinal(data);
    }

    /**
     * 加密<br>
     * 用公钥加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPublicKey(byte[] data, String key)
            throws Exception {
        // 对公钥解密
        byte[] keyBytes = decryptBASE64(key);

        // 取得公钥
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicKey = keyFactory.generatePublic(x509KeySpec);

        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        return cipher.doFinal(data);
    }

    /**
     * 加密<br>
     * 用私钥加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] encryptByPrivateKey(byte[] data, String key)
            throws Exception {
        // 对密钥解密
        byte[] keyBytes = decryptBASE64(key);

        // 取得私钥
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);

        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        return cipher.doFinal(data);
    }

    /**
     * 取得私钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPrivateKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PRIVATE_KEY);

        return encryptBASE64(key.getEncoded());
    }

    /**
     * 取得公钥
     *
     * @param keyMap
     * @return
     * @throws Exception
     */
    public static String getPublicKey(Map<String, Object> keyMap)
            throws Exception {
        Key key = (Key) keyMap.get(PUBLIC_KEY);

        return encryptBASE64(key.getEncoded());
    }

    /**
     * 初始化密钥
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Object> initKey() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator
                .getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(1024);

        KeyPair keyPair = keyPairGen.generateKeyPair();

        // 公钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        // 私钥
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        Map<String, Object> keyMap = new HashMap<String, Object>(2);

        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }
}
```