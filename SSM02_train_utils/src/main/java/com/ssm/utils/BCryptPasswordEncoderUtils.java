package com.ssm.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtils {
    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public static String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        String password = "admin";
        String pwd = encodePassword(password);
        System.out.println(bCryptPasswordEncoder.matches("admin","$2a$10$iGEVg0umZMt2Dan1geoE2.nWOvniTQ3v2Ce/X7SXKYVyqWGy/Vq4q"));
        System.out.println(pwd);
    }
}
