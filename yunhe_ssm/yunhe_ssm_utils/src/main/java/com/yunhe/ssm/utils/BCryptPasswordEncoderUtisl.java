package com.yunhe.ssm.utils;

/**
 * @author James
 * @create 2019-08-09 15:18
 */

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 加密工具类
 */
public class BCryptPasswordEncoderUtisl {


    private static BCryptPasswordEncoder bCryptPasswordEncoder =new BCryptPasswordEncoder();

    /**
     * 传入明文密码
     * @param password
     * @return 加密密码
     */
    public static String encoderPassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    /**
     * 测试调用加密方法
     * @param args
     */
    public static void main(String[] args){

        String password="123456";
        String pwd = encoderPassword(password);
        System.out.println(pwd);

        //$2a$10$bVXBydvaH8EcTtdJC.o7CertwzKdYWnwfxwg6m.hGXbxO1PjiBqBe
    }


}
