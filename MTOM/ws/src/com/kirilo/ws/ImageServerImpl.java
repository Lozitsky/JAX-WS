package com.kirilo.ws;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.jws.WebService;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.MTOM;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

@MTOM
@WebService(portName = "ImagePort",
        serviceName = "ImageService",
        targetNamespace = "http://cirilo.com/wsdl",
        endpointInterface = "com.kirilo.ws.ImageServer")
public class ImageServerImpl implements ImageServer {

    @PostConstruct
    private void init() {
        System.out.println("Server is published!");
    }

    //    https://mkyong.com/webservices/jax-ws/jax-ws-attachment-with-mtom/
    //    https://mkyong.com/java/java-read-a-file-from-resources-folder/
    @Override
    public Image downloadImage(String name) throws URISyntaxException {
        File file;
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(name);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + name);
        } else {
            file = new File(resource.toURI());
        }
        try {
            System.out.println(file.toURI());
            return ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String uploadImage(byte[] data) {
        if (data != null) {
            //store somewhere
            return "Upload Successful: " + data.length / 1024 + " kb";
        }
        throw new WebServiceException("Upload Failed!");
    }
}
