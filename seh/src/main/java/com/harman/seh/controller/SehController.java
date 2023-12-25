package com.harman.seh.controller;

import com.harman.seh.entity.Users;
import com.harman.seh.service.SehService;
import com.harman.seh.validation.UserDetailsValidation;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("user")
public class SehController {

    UserDetailsValidation userDetailsValidation = new UserDetailsValidation();

    @Autowired
    SehService sehService;

    @ModelAttribute("user")
    public Users users(){
        return new Users();
    }

    @GetMapping("/login")
    public String userLogin(){
        return "login";
    }

    @GetMapping("/signup")
    public String userSignUp(){
        return "signup";
    }

    @PostMapping("/signup/save")
    public String registration(@Valid @ModelAttribute("user") Users user,
                               BindingResult result,
                               Model model) {
        String password = user.getPassword();
        String email = user.getEmail();
        Users existing = sehService.findByEmail(email);
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (password.length()<8)
            {
                result.rejectValue("password", null, "Password must have at least 8 characters");
            }
        else if(!userDetailsValidation.passwordValidation(password))
            result.rejectValue("password", null, "invalid password\n Password must contain atleast one capital letter\n Password must contain atleast one digit");

        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "signup";
        }
        sehService.save(user);
        return "redirect:/user/welcome?email=" + user.getEmail() + "&success";

    }

    @GetMapping("/welcome")
    public String uponLogin(@RequestParam(name = "email", required = false, defaultValue = "") String email, Model model){
        Users user = sehService.findByEmail(email);
        System.out.println(user.getEmail());
        if (user != null) {
            String welcomeMessage = "Welcome " + user.getFirstName() + " " + user.getLastName();
            model.addAttribute("welcomeMessage", welcomeMessage);
        }

        return "homepage";
    }

}
