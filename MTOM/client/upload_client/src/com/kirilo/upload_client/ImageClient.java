package com.kirilo.upload_client;

import com.kirilo.ws.ImageServer;

import javax.imageio.ImageIO;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

// https://www.tutorialspoint.com/How-to-convert-Image-to-Byte-Array-in-java
public class ImageClient {
    public static void main(String[] args) throws IOException {
        Service service = Service.create(new URL("http://localhost:8080/image-service/ImageService?wsdl"),
                new QName("http://cirilo.com/wsdl", "ImageService"));

        ImageServer port = service.getPort(ImageServer.class);

        String name = "15.jpg";
        InputStream in = ImageServer.class.getResourceAsStream("/15.jpg");

        if (in == null) {
            throw new IllegalArgumentException("file not found! " + name);
        }

        BufferedImage image = ImageIO.read(in);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", bos);
        byte[] data = bos.toByteArray();
        System.out.println("Send data: " + data.length / 1024  + " kb");

        //enable MTOM in client
        BindingProvider bp = (BindingProvider) port;
        SOAPBinding binding = (SOAPBinding) bp.getBinding();
        binding.setMTOMEnabled(true);

        String status = port.uploadImage(data);
        System.out.println("imageServer.uploadImage() : " + status);
    }
}
