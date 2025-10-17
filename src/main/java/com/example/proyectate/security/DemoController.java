package com.example.proyectate.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo")
public class DemoController {

    @GetMapping
    @PreAuthorize("hasRole('USUARIO')")
    public String sayHello() {
        return "Hello USUARIO from secured endpoint";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public String sayHelloAdmin() {
        return "Hello ADMINISTRADOR from secured endpoint";
    }
}