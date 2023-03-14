package br.com.gustavobarbozamarques.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public String getAdminResource(Authentication authentication) {
        var jwt = (Jwt) authentication.getPrincipal();
        return "Hello Admin," +
                "\nName: " + jwt.getClaim("name") +
                "\nUsername: " + jwt.getClaim("preferred_username") +
                "\nEmail: " + jwt.getClaim("email");
    }

    @GetMapping("/guest")
    @PreAuthorize("hasAuthority('GUEST_USER')")
    public String getGuestResource(Authentication authentication) {
        var jwt = (Jwt) authentication.getPrincipal();
        return "Hello Guest," +
                "\nName: " + jwt.getClaim("name") +
                "\nUsername: " + jwt.getClaim("preferred_username") +
                "\nEmail: " + jwt.getClaim("email");
    }
}
