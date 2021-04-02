package com.kirilo.webclient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;
import java.io.IOException;
import java.io.PrintWriter;
import com.kirilo.helloservice.HelloService;
import com.kirilo.helloservice.Hello;


@WebServlet(name = "HelloServlet", urlPatterns = "/HelloServlet")
public class HelloServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation =
            "http://localhost:8080/hello-service/HelloService?WSDL")
    private HelloService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
//        try (PrintWriter wr = resp.getWriter()) {
        PrintWriter wr = resp.getWriter();
        wr.println("<html lang=\"en\">");
        wr.println("<head>");
        wr.println("<title>Servlet HelloServlet</title>");
        wr.println("</head>");
        wr.println("<body>");
        wr.println("<h1>Servlet HelloServlet at " +
                req.getContextPath() + "</h1>");
        wr.println("<p>" + sayHello("world") + "</p>");
        wr.println("</body>");
        wr.println("</html>");
//        }
    }

    private String sayHello(String world) {
        Hello port =
                service.getHelloPort();
        return port.greet(world);
    }

    @Override
    public String getServletInfo() {
        return super.getServletInfo();
    }
}
