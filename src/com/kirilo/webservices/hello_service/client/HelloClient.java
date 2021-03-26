package com.kirilo.webservices.hello_service.client;

public class HelloClient {
    public static void main(String[] args) {
        HelloService helloService = new HelloService();
        Hello port = helloService.getHelloPort();
        String greet = port.greet("someone");
        System.out.println(greet);
    }
}
