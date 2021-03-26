package com.kirilo.webservices.hash_service.server;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.jws.WebService;
import javax.jws.WebMethod;

@WebService
public class MD5WebService {
    @WebMethod
    public String hashString(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] bytes = input.getBytes();
            byte[] hashedBytes = digest.digest(bytes);
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < hashedBytes.length; i++) {
                buffer.append(Integer.toString((hashedBytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
