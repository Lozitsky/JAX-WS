package com.kirilo.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.awt.*;
import java.net.URISyntaxException;

@WebService(
        targetNamespace = "http://cirilo.com/wsdl"
)
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ImageServer {
    @WebMethod
    Image downloadImage(String name) throws URISyntaxException;

    @WebMethod
    String uploadImage(byte[] data);
}

