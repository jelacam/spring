package com.example.codingchallenge.securityconfig;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Helper {

    public static String Generate(String pass) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(pass.getBytes(StandardCharsets.UTF_8));
            String passwordHash = hash.toString();
            return passwordHash;
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return pass;
        }
    }
}
