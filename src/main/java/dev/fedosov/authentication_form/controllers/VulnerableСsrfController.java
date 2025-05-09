package dev.fedosov.authentication_form.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/csrf-demo")
public class VulnerableСsrfController {

    private final InMemoryUserDetailsManager userDetailsManager;

    @Autowired
    public VulnerableСsrfController(UserDetailsService userDetailsService) {
        this.userDetailsManager = (InMemoryUserDetailsManager) userDetailsService;
    }

    @GetMapping("/account-info")
    public String show(Authentication authentication, Model model) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        model.addAttribute("username", userDetails.getUsername());
        return "csrfDemo";
    }

    @PostMapping("/update-password")
    public String updatePassword(Authentication authentication, @RequestParam("newPassword") String newPassword) {
        String username = authentication.getName();
        UserDetails updatedUser = User.withDefaultPasswordEncoder()
                .username(username)
                .password(newPassword)
                .roles(userDetailsManager.loadUserByUsername(username).getAuthorities().stream()
                        .map(a -> a.getAuthority().replace("ROLE_", ""))
                        .toArray(String[]::new))
                .build();

        userDetailsManager.updateUser(updatedUser);

        return "redirect:/csrf-demo/account-info";
    }
}
