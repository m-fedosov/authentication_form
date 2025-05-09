package dev.fedosov.authentication_form.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String getHello(Authentication authentication, Model model) {
        model.addAttribute("username", authentication.getName());
        return "hello";
    }
}
