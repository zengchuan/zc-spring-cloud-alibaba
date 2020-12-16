package com.zengc.auth.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordUtils {

    public static void main(String args[]) {
        System.out.println(new BCryptPasswordEncoder().encode("8b565053-6b8a-4643-882e-08c5ea60f287"));
    }

}
