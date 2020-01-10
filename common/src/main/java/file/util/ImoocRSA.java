package file.util;

import org.apache.commons.codec.binary.Hex;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/11/8
 * @description
 */
public class ImoocRSA {

    private static String src = "imooc security rsa";

    public static void main(String[] args) {
        jdkRSA();
    }

    public static String key = "112412k3kj412kh41j2kh41k234h123";

    public static RSAPublicKey rsaPublicKey = null;

    public static RSAPrivateKey rsaPrivateKey = null;


    public static void jdkRSA() {
        try {
            //1.初始化密钥
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            RSAPublicKey rsaPublicKey = (RSAPublicKey)keyPair.getPublic();
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey)keyPair.getPrivate();
            System.out.println(new String(rsaPrivateKey.getEncoded()));

            //2.执行签名
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Signature signature = Signature.getInstance("MD5withRSA");
            signature.initSign(privateKey);
            signature.update(src.getBytes());
            byte[] result = signature.sign();
            System.out.println("jdk rsa sign : " + Hex.encodeHexString(result));

            //3.验证签名
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
            keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            signature = Signature.getInstance("MD5withRSA");
            signature.initVerify(publicKey);
            signature.update(src.getBytes());
            boolean bool = signature.verify(result);
            System.out.println("jdk rsa verify : " + bool);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String doSignature() throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        getPubKey();
        getPriKey();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(512);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(privateKey);
        signature.update(src.getBytes());
        byte[] result = signature.sign();
        System.out.println("jdk rsa sign : " + Hex.encodeHexString(result));
        return Hex.encodeHexString(result);
    }

    public static RSAPublicKey getPubKey() throws NoSuchAlgorithmException {
        //1.初始化密钥
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(512);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        if(rsaPublicKey == null) {
            return (RSAPublicKey)keyPair.getPublic();
        } else {
            return rsaPublicKey;
        }
    }

    public static RSAPrivateKey getPriKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(512);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        if(rsaPrivateKey == null) {
            return (RSAPrivateKey)keyPair.getPublic();
        } else {
            return rsaPrivateKey;
        }
    }

}
