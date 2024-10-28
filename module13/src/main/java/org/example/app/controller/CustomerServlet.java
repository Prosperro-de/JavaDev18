package org.example.app.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.app.config.TemplateConfig;
import org.example.app.model.Customer;
import org.example.app.service.CustomerService;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/", "/createCustomerForm", "/findCustomerByIdForm", "/findById",
        "/findAllCustomers", "/createCustomer"})
public class CustomerServlet extends HttpServlet {
    private final TemplateConfig templateConfig = new TemplateConfig();
    private final CustomerService customerService = new CustomerService();

    public CustomerServlet() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        Context context = new Context();
        switch (servletPath) {
            case "/createCustomerForm" -> {
                context.setVariable("action", "createCustomerForm");
                templateConfig.process("index", context, resp);
            }
            case "/findCustomerByIdForm" -> {
                context.setVariable("action", "findByIdForm");
                templateConfig.process("index", context, resp);
            }
            case "/findById" -> {
                String customerId = req.getParameter("customerId");
                Customer customerById = customerService.findById(Long.parseLong(customerId));
                context.setVariable("action", "customerDetails");
                context.setVariable("customer", customerById);
                templateConfig.process("index", context, resp);
            }
            case "/findAllCustomers" -> {
                List<Customer> customers = customerService.findAll();
                context.setVariable("action", "allCustomerDetails");
                context.setVariable("customers", customers);
                templateConfig.process("index", context, resp);
            }
            default -> templateConfig.process("index", context, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getParameterMap()
//                .forEach((k, v) -> System.out.println(k + " : " + v[0]));
        try {
//            customerService.saveCustomer(req.getParameterMap());
            customerService.saveCustomer(req.getReader());
            resp.setStatus(200);
        } catch (Exception e) {
            resp.setStatus(500);
        }
    }
}
