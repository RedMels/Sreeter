package ru.staticvoid.sreeter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.staticvoid.sreeter.domain.Role;
import ru.staticvoid.sreeter.domain.User;
import ru.staticvoid.sreeter.repos.UserRepo;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("message", "");
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser (User user, Map<String, Object> model) {
        User userFormDB = userRepo.findByUsername(user.getUsername());

        if (userFormDB != null) {
            model.put("message", "user already exists");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepo.save(user);

        return "redirect:/login";
    }

}
