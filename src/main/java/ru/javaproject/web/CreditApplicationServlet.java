package ru.javaproject.web;

import org.jboss.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreditApplicationServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(CreditApplicationServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("redirect 'method get' to credit application");

        resp.sendRedirect("createCreditApplication.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("redirect 'method post' to credit application");

        resp.sendRedirect("");
    }
}
