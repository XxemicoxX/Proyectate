package com.example.proyectate.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("demo")
public class DemoController {

    @GetMapping
    @PreAuthorize("user")
    public String sayHello() {
        return "Hello USUARIO from secured endpoint";
    }

    @GetMapping("admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String sayHelloAdmin() {
        return "Hello ADMINISTRADOR from secured endpoint";
    }
    
}