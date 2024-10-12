package com.one.social_media.controller;

import com.one.social_media.entity.User;
import com.one.social_media.service.UserDetailsImpl;
import com.one.social_media.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request) {
        return "login";
    }

    @GetMapping("/dashboard")
    @PreAuthorize("(hasRole('ADMIN'))")
    public String dashboard( HttpSession session){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            Long userId = userDetails.getId();

            User user = userService.findById(userId).orElse(null);
            session.setAttribute("user", user);

        }
        return "dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        SecurityContextHolder.clearContext();
        return "redirect:/login";
    }




}
