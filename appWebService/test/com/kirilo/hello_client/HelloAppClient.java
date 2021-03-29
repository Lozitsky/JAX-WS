package com.kirilo.hello_client;

import org.apache.commons.io.IOUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import static org.junit.Assert.assertTrue;

public class HelloAppClient {
    private static EJBContainer container;

    @BeforeClass
    public static void setUp() throws Exception {
        final Properties properties = new Properties();
        properties.setProperty("openejb.embedded.remotable", "true");
        container = EJBContainer.createEJBContainer(properties);
    }

    @Before
    public void inject() throws NamingException {
        if (container != null) {
            container.getContext().bind("inject", this);
        }
    }

    @AfterClass
    public static void close() {
        if (container != null) {
            container.close();
        }
    }

    @Test
    public void wsdlExists() throws IOException {
        final URL url = new URL("http://localhost:8080/service/webservices/Hello?wsdl");
        assertTrue(IOUtils.readLines(url.openStream()).size() > 0);
        assertTrue(IOUtils.readLines(url.openStream()).toString().contains("HelloService"));
    }
}
