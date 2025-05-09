package dev.fedosov.authentication_form.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/xss-demo")
public class VulnerableXssController {

    @GetMapping("/product-search")
    public String greet(@RequestParam("product_name") String productName, Model model) {
        model.addAttribute("product_name", productName);
        return "xssDemo";
    }
}
