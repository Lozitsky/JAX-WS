package com.kirilo.webservices.hello_service.server;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class Hello {
    @WebMethod
    public String greet(String name) {
        return "Hello " + name;
    }
}
