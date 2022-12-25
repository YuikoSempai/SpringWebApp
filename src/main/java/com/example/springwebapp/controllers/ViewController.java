package com.example.springwebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    @RequestMapping(value={"/form","/table","/registration","/login"})
    public String getViews(){
        return "/";
    }
}
