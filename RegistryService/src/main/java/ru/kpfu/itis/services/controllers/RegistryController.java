package ru.kpfu.itis.services.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.kpfu.itis.services.Reciever;
import ru.kpfu.itis.services.dto.CatDto;
import ru.kpfu.itis.services.dto.UserDto;
import ru.kpfu.itis.services.services.RegistryService;

import java.util.List;

@Controller
public class RegistryController {


    @Autowired
    private RestTemplate template;

    @Autowired
    private Reciever reciever;

    @Autowired
    private RegistryService service;

    @GetMapping("users")
    public String getUsers(Model model) {
        List<UserDto> users = this.service.getAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/registry")
    public String getRegistration(Model model) {
        //CatDto catDto = template.getForEntity("http://CAT/cats/search", CatDto.class).getBody();
        //model.addAttribute("url", catDto.getUrl());
        return "form";
    }



    @PostMapping(value = "/registry", params = "user")
//    @ResponseBody
    public String registry(@ModelAttribute(name = "userObj") UserDto userDto) {
        String catUrl = this.reciever.getCatUrl();
        String username = userDto.getName();
        userDto.setUrl(catUrl);
        this.service.sendToSave(userDto);

//        return "users";
//        return ResponseEntity.ok("{\"sendToUserQueue\":\"" + userDto.getName() + ", " + userDto.getUrl() + "\"}");
        return "redirect:/users";
    }

}
