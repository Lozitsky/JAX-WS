
package com.kirilo.webservices.hash_service.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "MD5WebService", targetNamespace = "http://server.hash_service.webservices.kirilo.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface MD5WebService {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "hashString", targetNamespace = "http://server.hash_service.webservices.kirilo.com/", className = "com.kirilo.webservices.hash_service.client.HashString")
    @ResponseWrapper(localName = "hashStringResponse", targetNamespace = "http://server.hash_service.webservices.kirilo.com/", className = "com.kirilo.webservices.hash_service.client.HashStringResponse")
    @Action(input = "http://server.hash_service.webservices.kirilo.com/MD5WebService/hashStringRequest", output = "http://server.hash_service.webservices.kirilo.com/MD5WebService/hashStringResponse")
    public String hashString(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}
