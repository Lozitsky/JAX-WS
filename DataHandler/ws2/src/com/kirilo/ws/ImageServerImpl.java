package com.kirilo.ws;

import com.sun.xml.ws.developer.StreamingAttachment;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.PostConstruct;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.soap.MTOM;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

// https://docs.oracle.com/cd/E14571_01/web.1111/e13734/mtom.htm#WSADV141
// https://mkyong.com/webservices/jax-ws/jax-ws-attachment-with-mtom/
@StreamingAttachment(parseEagerly = true, memoryThreshold = 400L)
@MTOM
@WebService(portName = "ImagePort2",
        serviceName = "ImageService2",
        targetNamespace = "http://kirilo.com/wsdl",
        endpointInterface = "com.kirilo.ws.ImageServer2")
public class ImageServerImpl implements ImageServer2 {
    public static final int DEFAULT_BUFFER_SIZE = 8192;

    @PostConstruct
    private void init() {
        System.out.println("Server is published!");
    }

    @Override
//    @XmlMimeType("application/octet-stream")
    @WebMethod
    public DataHandler downloadImage(String name) throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(name);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + name);
        }
        return new DataHandler(new FileDataSource(new File(resource.toURI())));
    }

    @Override
    public String uploadImage(String fileName, /*@XmlMimeType("application/octet-stream")*/ DataHandler data) {
        if (data != null) {
            //store somewhere
            try {
                if (data != null) {
                    File file;
                    try (InputStream in = data.getInputStream()) {
                        if (in == null) {
                            throw new IllegalArgumentException("file not found! " + fileName);
                        }
                        file = new File(fileName);
                        copyInputStreamToFile(in, file);
                    }

                    System.out.println("File " + fileName + " upload to server!");
                    System.out.println(file.getAbsolutePath());
                }
            } catch (Exception e) {
                throw new WebServiceException(e);
            }
            return "Upload Successful!";
        }
        throw new WebServiceException("Upload Failed!");
    }

    //    https://mkyong.com/java/how-to-convert-inputstream-to-file-in-java/
    private static void copyInputStreamToFile(InputStream inputStream, File file) throws IOException {
        // append = false
        try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
            int read;
            byte[] bytes = new byte[DEFAULT_BUFFER_SIZE];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        }
    }
}
