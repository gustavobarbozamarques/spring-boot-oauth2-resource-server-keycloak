package br.com.gustavobarbozamarques.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @GetMapping
    public String getResource(Authentication authentication) {
        var jwt = (Jwt) authentication.getPrincipal();
        return "Hello," +
                "\nName: " + jwt.getClaim("name") +
                "\nUsername: " + jwt.getClaim("preferred_username") +
                "\nEmail: " + jwt.getClaim("email");
    }
}
