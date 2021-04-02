package com.kirilo.appclient;

import com.kirilo.helloservice.HelloService;
import com.kirilo.helloservice.Hello;

import javax.xml.ws.WebServiceRef;

public class HelloAppClient {

    @WebServiceRef(wsdlLocation = "http://localhost:8080/hello-service/HelloService?WSDL")
    private static HelloService service;

    public static void main(String[] args) {
        System.out.println(sayHello("world"));
    }

    private static String sayHello(java.lang.String arg0) {
        com.kirilo.helloservice.Hello port = null;
        try {
            port = service.getHelloPort();

        } catch (Exception e) {
            port = new HelloService().getHelloPort();
        }
        return port.greet(arg0);
    }

}
