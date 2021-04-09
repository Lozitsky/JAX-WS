package com.kirilo.calculator;

import javax.ejb.Singleton;
import javax.jws.HandlerChain;
import javax.jws.WebService;

@Singleton
@WebService(
        portName = "CalculatorPort",
        serviceName = "CalculatorService",
        targetNamespace = "http://superbiz.org/wsdl",
        endpointInterface = "com.kirilo.calculator.Calculator")
@HandlerChain(file = "handlers.xml")
public class CalculatorImp implements Calculator{
    @Override
    public int sum(int add1, int add2) {
        return add1 + add2;
    }

    @Override
    public int multiply(int mul1, int mul2) {
        return mul1 * mul2;
    }
}
