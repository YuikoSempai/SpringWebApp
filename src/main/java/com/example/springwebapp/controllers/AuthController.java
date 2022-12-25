package com.example.springwebapp.controllers;

import com.example.springwebapp.controllers.DTO.UserInfoDTO;
import com.example.springwebapp.exceptionsData.exceptions.UserNotFoundException;
import com.example.springwebapp.models.UserInfo;
import com.example.springwebapp.services.RegistrationServiceImpl;
import com.example.springwebapp.util.UserInfoValidator;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin
@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserInfoValidator userInfoValidator;
    private final RegistrationServiceImpl registrationService;

    @Autowired
    public AuthController(UserInfoValidator userInfoValidator, RegistrationServiceImpl registrationService) {
        this.userInfoValidator = userInfoValidator;
        this.registrationService = registrationService;
    }


    @GetMapping("/user")
    @ResponseBody
    public String currentUser(Principal principal) {
        try {
            return principal.getName();
        } catch (Exception e) {
            throw new UserNotFoundException("you are not login in yet");
        }
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "/registration";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "/login";
    }

    @ResponseBody
    @PostMapping("/registration")
    public String performRegistration(@Valid @RequestBody UserInfoDTO userInfoDTO, HttpServletResponse response) {
        UserInfo userInfo = new UserInfo(userInfoDTO.getUsername(), userInfoDTO.getPassword(), userInfoDTO.getRoles());
        if (userInfoValidator.checkUserIsExists(userInfo)) {
            throw new RuntimeException("user already exists");
        }
        registrationService.register(userInfo);
        return "true";
    }


}