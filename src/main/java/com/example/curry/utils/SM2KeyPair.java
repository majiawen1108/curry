package com.example.curry.utils;

import cn.hutool.core.util.HexUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;

/**
 * @author jw.ma
 * @title: SM2KeyPair
 * @description: TODO
 * @date 2022/6/8 10:14
 */
@Data
@AllArgsConstructor
public class SM2KeyPair {
    private static String FOUR = "04";
    /**
     * 公钥
     */
    private String publicKey;

    /**
     * 前端公钥
     */
    private String publicQKey;

    /**
     * 私钥
     */
    private String privateKey;


    /**
     * 生成密钥对
     * @return
     */
    public static SM2KeyPair getSm2KeyPair() {
        SM2 sm2= SmUtil.sm2();
        // 公钥
        String publicKey=sm2.getPublicKeyBase64();
        // 前端公钥
        String publicQkey = HexUtil.encodeHexStr(((BCECPublicKey)sm2.getPublicKey()).getQ().getEncoded(false));
        // 私钥
        String privateKey=sm2.getPrivateKeyBase64();
        return new SM2KeyPair(publicKey,publicQkey,privateKey);
    }

    /**
     * 公钥加密
     * @param data
     * @param publicKey
     * @return
     */
    public static String encrypt(String data, String publicKey) {
        SM2 sm2=SmUtil.sm2(null,publicKey);
        String encryptStr = sm2.encryptBcd(data, KeyType.PublicKey);
        return encryptStr;
    }

    /**
     * 私钥解密 公钥加密密文
     * @param encryptStr
     * @param privateKey
     * @return
     */
    public static String decrypt(String encryptStr, String privateKey){
        if(!encryptStr.startsWith(FOUR)){
            encryptStr=FOUR.concat(encryptStr);
        }
        SM2 sm2=SmUtil.sm2(privateKey,null);
        String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd(encryptStr, KeyType.PrivateKey));
        return decryptStr;
    }

    /**
     * 私钥签名
     * @param data
     * @param privateKey
     * @return
     */
    public static String signByPrivateKey(String data, String privateKey){
        SM2 sm2= SmUtil.sm2(privateKey,null);
        String sign = sm2.signHex(HexUtil.encodeHexStr(data));
        return sign;
    }

    /**
     * 公钥验签
     * @param data
     * @param publicKey
     * @param signature
     * @return
     */
    public static boolean verifyByPublicKey(String data, String publicKey, String signature){
        SM2 sm2= SmUtil.sm2(null,publicKey);
        boolean verify = sm2.verifyHex(HexUtil.encodeHexStr(data), signature);
        return verify;
    }

}
