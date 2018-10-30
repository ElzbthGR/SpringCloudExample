package ru.kpfu.itis.services.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.services.dto.CatDto;
import ru.kpfu.itis.services.services.CatService;

@RestController
public class CatController {

    @Autowired
    private CatService service;

    @GetMapping("cats/search")
    public ResponseEntity<CatDto> getCat() {
        this.service.send();
        return ResponseEntity.ok(service.getCat());
    }
}
