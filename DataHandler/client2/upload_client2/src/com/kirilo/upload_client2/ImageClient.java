package com.kirilo.upload_client2;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.soap.MTOMFeature;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

import com.kirilo.ws.ImageServer2;

import com.sun.xml.ws.developer.JAXWSProperties;
import com.sun.xml.ws.developer.StreamingDataHandler;

// https://docs.oracle.com/cd/E14571_01/web.1111/e13734/mtom.htm#WSADV141
// https://stackoverflow.com/questions/17552597/jax-wsclient-casting-a-stub-to-a-bindingprovider
public class ImageClient {
    //    public static final int DEFAULT_BUFFER_SIZE = 8192;
    public static void main(String[] args) throws IOException, URISyntaxException {
        Service service = Service.create(new URL("http://localhost:8080/image-service/ImageService2?wsdl"),
                new QName("http://kirilo.com/wsdl", "ImageService2"));

        MTOMFeature feature = new MTOMFeature();
        ImageServer2 port = service.getPort(ImageServer2.class);
        Map<String, Object> ctxt = ((BindingProvider) port).getRequestContext();
        ctxt.put(JAXWSProperties.HTTP_CLIENT_STREAMING_CHUNK_SIZE, 8192);
        String name = "15.jpg";

        DataHandler dh = new DataHandler(new FileDataSource("/" + name));

        String status = port.uploadImage(name, dh);
        System.out.println("imageServer.uploadImage() : " + status);
    }
}
