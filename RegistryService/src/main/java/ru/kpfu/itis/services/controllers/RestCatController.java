package ru.kpfu.itis.services.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.services.dto.Response;
import ru.kpfu.itis.services.dto.UserDto;
import ru.kpfu.itis.services.services.RegistryService;

@RestController
public class RestCatController {

    @Autowired
    private RegistryService registryService;
    
    @GetMapping("/cat")
    public Response findCat() {
        this.registryService.send();
        return new Response("OK", "OK");
    }
}
