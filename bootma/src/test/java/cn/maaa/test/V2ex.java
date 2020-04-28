package cn.maaa.test;

import org.springframework.util.Base64Utils;

/**
 * https://www.v2ex.com
 *
 * @author maaa
 * @date 2020年04月28日 14:23
 */
public class V2ex {
    public static void main(String[] args) {
        //深圳- Java 开发（两年多经验）-求职
        System.out.println(new String(Base64Utils.decodeFromString("aHR0cHM6Ly9jaGluYWxoci5naXRodWIuaW8vcG9zdC9yZXN1bWU=")));
        //深圳龙华宝能科技园
        System.out.println(new String(Base64Utils.decodeFromString("cm9tYW50aWMuc3RvbmVAZ21haWwuY29tCiA=")));

    }
}
