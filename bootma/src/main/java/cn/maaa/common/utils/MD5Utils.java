package cn.maaa.common.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5Utils {
    private static final String SALT = "mazh";

    private static final int HASH_ITERATIONS = 2;

    public static String encrypt(String pswd) {
        String newPassword = new Md5Hash(pswd, SALT, HASH_ITERATIONS).toHex();
        return newPassword;
    }

    public static String encrypt(String pswd,String salt) {
        String newPassword = new Md5Hash(pswd, salt, HASH_ITERATIONS).toHex();
        return newPassword;
    }

    public static void main(String[] args) {
        String username ="admin";
        String psw ="111111";
        System.out.println(encrypt(psw));
        System.out.println(encrypt(psw,"maaa"));
    }
}
