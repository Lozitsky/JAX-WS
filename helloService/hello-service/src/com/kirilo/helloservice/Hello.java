package com.kirilo.helloservice;

import javax.jws.WebMethod;
import javax.jws.WebService;

/*@WebService(targetNamespace = "http://helloservice.kirilo.com",
        portName = "HelloPort",
        serviceName = "HelloService",
        endpointInterface = "com.kirilo.helloservice.Hello",
        wsdlLocation = "./target/hello-service/WEB-INF/wsdl/HelloService.wsdl")*/
@WebService
public class Hello {
    private final String message = "Hello, ";

    public Hello() {
    }

    @WebMethod
    public String greet(String name) {
        return message + name + ".";
    }
}
