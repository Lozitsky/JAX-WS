package com.kirilo.hello_service;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;


@Stateless
@WebService(
        portName = "HelloPort",
        serviceName = "HelloService",
        targetNamespace = "http://superbiz.org/wsdl"
)
public class Hello {
    private final String message = "Hello, ";

    @WebMethod
    public String greet(String name) {
        return message + name + ".";
    }
}