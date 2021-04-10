package com.kirilo.download_client2;

import javax.activation.DataHandler;
import javax.swing.*;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.soap.MTOMFeature;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.kirilo.ws.ImageServer2;
import com.kirilo.ws.URISyntaxException_Exception;

import com.sun.xml.ws.developer.JAXWSProperties;
import com.sun.xml.ws.developer.StreamingDataHandler;

public class ImageClient {

    public static void main(String[] args) throws MalformedURLException, URISyntaxException_Exception {
        Service service = Service.create(new URL("http://localhost:8080/image-service/ImageService2?wsdl"),
                new QName("http://kirilo.com/wsdl", "ImageService2"));

        MTOMFeature feature = new MTOMFeature();
        ImageServer2 port = service.getPort(ImageServer2.class);

        DataHandler dhn = port.downloadImage("taras.jpg");
        Image content = null;
        try {
            content = (Image) dhn.getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //display it in frame
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        JLabel label = new JLabel(new ImageIcon(content));
        frame.add(label);
        frame.setVisible(true);

        System.out.println("imageServer.downloadImage() : Download Successful!");
    }
}
