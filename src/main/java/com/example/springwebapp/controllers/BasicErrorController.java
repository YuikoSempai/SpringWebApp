package com.example.springwebapp.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
@CrossOrigin
@Controller
public class BasicErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        return "error";
    }
}