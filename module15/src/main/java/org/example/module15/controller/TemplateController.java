package org.example.module15.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TemplateController {

    @GetMapping("/createCustomerForm")
    public ModelAndView createCustomerForm() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("action", "createCustomerForm");
        return modelAndView;
    }

    @GetMapping("/findCustomerByIdForm")
    public ModelAndView findCustomerByIdForm() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("action", "findByIdForm");
        return modelAndView;
    }
}
