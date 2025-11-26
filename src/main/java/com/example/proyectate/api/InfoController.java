package com.example.proyectate.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("info")
public class InfoController {

  @GetMapping()
  public String getInformation() {
    return "Spring Boot App with JWT";
  }

}
