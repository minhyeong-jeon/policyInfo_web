package com.policyInfo.policyInfo.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Model model,
                        @RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception) {

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/";
    }
}
