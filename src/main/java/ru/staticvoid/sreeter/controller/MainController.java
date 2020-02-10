package ru.staticvoid.sreeter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.staticvoid.sreeter.domain.Message;
import ru.staticvoid.sreeter.domain.User;
import ru.staticvoid.sreeter.repos.MessageRepo;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private MessageRepo msgRepo;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting.ftlx";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Message> messages = msgRepo.findAll();

        model.put("messages", messages);

        return "main.ftlx";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Map<String, Object> model) {
        Message message = new Message(text, tag, user);

        msgRepo.save(message);

        Iterable<Message> messages = msgRepo.findAll();

        model.put("messages", messages);

        return "main.ftlx";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Message> messages;

        if (filter != null && !filter.isEmpty()) {
            messages = msgRepo.findByTag(filter);
        } else {
            messages = msgRepo.findAll();
        }

        model.put("messages", messages);

        return "main.ftlx";
    }

}