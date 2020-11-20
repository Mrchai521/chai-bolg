package com.cxf.mblog.base.utils;

import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author xfchai
 * @ClassName MD5.java
 * @Description MD5加密
 * @createTime 2020/11/16 16:33:00
 */
public class MD5 {
    /**
     * 对字符串进行Md5加密
     * @param input 原文
     * @return
     */
    public static String md5(String input) {
        byte[] code = null;
        try {
            code = MessageDigest.getInstance("md5").digest(input.getBytes());
        } catch (NoSuchAlgorithmException e) {
            code = input.getBytes();
        }
        BigInteger bi = new BigInteger(code);
        return bi.abs().toString(32).toUpperCase();
    }

    /**
     * 对字符串进行md5加密加盐值
     * @param input
     * @param salt
     * @return
     */
    public static String md5(String input,String salt){
        if(StringUtils.isEmpty(salt)) {
            salt = "";
        }
        return md5(salt + md5(input));
    }
    /**
     * 文件md5计算
     *
     * @param bytes
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String md5File(byte[] bytes)  {
        byte[] code = new byte[0];
        try {
            code = MessageDigest.getInstance("md5").digest(bytes);
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
        BigInteger bi = new BigInteger(code);
        return bi.abs().toString(32).toUpperCase();
    }
}
