package com.medical.store;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String rawPassword = "Bhushan@Girme2026"; // 🔁 change this

        String encodedPassword = encoder.encode(rawPassword);

        System.out.println("Encoded password:");
        System.out.println(encodedPassword);
    }
}