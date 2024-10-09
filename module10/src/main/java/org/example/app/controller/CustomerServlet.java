package org.example.app.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.app.service.CustomerService;

import java.io.IOException;

@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
    private CustomerService customerService = new CustomerService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO: parse data from request
//        req.getParameterMap()
//                .forEach((k, v) -> System.out.println(k + " : " + v[0]));
        try {
            customerService.saveCustomer(req.getParameterMap());
            resp.setStatus(200);
        } catch (Exception e) {
            resp.setStatus(500);
        }



        //TODO: save customer to database
    }
}
