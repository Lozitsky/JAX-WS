package com.kirilo.calculator;

import org.w3c.dom.Node;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Collections;
import java.util.Set;

public class Increment implements SOAPHandler<SOAPMessageContext> {
    @Override
    public Set<QName> getHeaders() {
        return Collections.emptySet();
    }

    @Override
    public boolean handleMessage(SOAPMessageContext soapMessageContext) {
        try {
            final SOAPMessage message = soapMessageContext.getMessage();
            final SOAPBody body = message.getSOAPBody();
            final String localName = body.getFirstChild().getLocalName();

            if ("sumResponse".equals(localName) || "multiplyResponse".equals(localName)) {
                final Node responseNode = body.getFirstChild();
                final Node returnNode = responseNode.getFirstChild();
                final Node intNode = returnNode.getFirstChild();

                final int value = new Integer(intNode.getNodeValue());
                intNode.setNodeValue(Integer.toString(value + 1));
            }

            return true;
        } catch (SOAPException e) {
            return false;
        }
    }

    @Override
    public boolean handleFault(SOAPMessageContext soapMessageContext) {
        return true;
    }

    @Override
    public void close(MessageContext messageContext) {

    }
}
