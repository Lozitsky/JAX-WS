package com.kirilo.ws;

import javax.activation.DataHandler;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlMimeType;
import java.net.URISyntaxException;

@WebService(
        targetNamespace = "http://kirilo.com/wsdl"
)
interface ImageServer2 {
    @WebMethod
    @XmlMimeType("application/octet-stream")
    DataHandler downloadImage(String name) throws URISyntaxException;

    @WebMethod
    String uploadImage(String fileName, @XmlMimeType("application/octet-stream") DataHandler data);
}

