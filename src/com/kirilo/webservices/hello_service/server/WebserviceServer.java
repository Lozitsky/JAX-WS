package com.kirilo.webservices.hello_service.server;

import javax.xml.ws.Endpoint;

// https://www.codejava.net/java-ee/web-services/create-client-server-application-for-web-service-in-java
public class WebserviceServer {
    public static void main(String[] args) {
        String URI = "http://localhost:8989/hellows";
        Hello hello = new Hello();
        Endpoint.publish(URI, hello);
        System.out.println("Webservice server started at: " + URI);
    }
}
