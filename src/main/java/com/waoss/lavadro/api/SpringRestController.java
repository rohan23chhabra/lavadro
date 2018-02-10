package com.waoss.lavadro.rest;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lavadro")
public class SpringRestController {

    @RequestMapping("/")
    public String indexMethod() {
        return "Index page";
    }

    @RequestMapping("/users")
    public String getUsers() {
        return "redirect:/api/data/users";
    }

}
