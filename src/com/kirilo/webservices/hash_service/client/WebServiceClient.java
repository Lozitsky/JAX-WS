package com.kirilo.webservices.hash_service.client;

public class WebServiceClient {
    public static void main(String[] args) {
        MD5WebServiceService service = new MD5WebServiceService();
        MD5WebService servicePort = service.getMD5WebServicePort();
        String hashString = servicePort.hashString("admin");
        System.out.println("MD5 hash string: " + hashString);
    }
}
