package com.security.controllers;


import com.security.model.ApplicationUser;
import com.security.model.LoginResponseDTO;
import com.security.model.RegistrationDTO;
import com.security.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService service;

    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistrationDTO body){
        return service.registerUser(body.getUsername(), body.getPassword());
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO body){
        System.out.println("LoginResponseDTO in AuthenticationController");
        return service.loginUser(body.getUsername(), body.getPassword());
    }
}
