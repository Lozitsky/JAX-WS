package com.kirilo.download_client;

import com.kirilo.ws.ImageServer;
import com.kirilo.ws.URISyntaxException_Exception;

import javax.swing.*;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageClient {

    public static void main(String[] args) throws MalformedURLException, URISyntaxException_Exception {

        Service service = Service.create(new URL("http://localhost:8080/image-service/ImageService?wsdl"),
                new QName("http://cirilo.com/wsdl", "ImageService"));

        ImageServer port = service.getPort(ImageServer.class);

        byte[] image = port.downloadImage("taras.jpg");

        //display it in frame
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        JLabel label = new JLabel(new ImageIcon(image));
        frame.add(label);
        frame.setVisible(true);

        System.out.println("imageServer.downloadImage() : Download Successful!");
    }
}
