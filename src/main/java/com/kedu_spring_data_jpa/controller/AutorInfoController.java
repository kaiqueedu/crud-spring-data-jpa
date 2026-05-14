package com.kedu_spring_data_jpa.controller;

import com.kedu_spring_data_jpa.entity.AutorInfo;
import com.kedu_spring_data_jpa.service.AutorInfoService;
import com.kedu_spring_data_jpa.service.AutorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("info")
public class AutorInfoController {

    private final AutorInfoService service;

    public AutorInfoController(AutorInfoService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public AutorInfo getAutorInfoById(@PathVariable Long id){
        return service.findById(id);
    }

    @GetMapping("/cargo")
    public List<AutorInfo> getContainsCargo(@RequestParam String cargo){
        return service.findAllContainsCargo(cargo);
    }

    @GetMapping("/cargo-empresa")
    public List<AutorInfo> getContainsCargo(@RequestParam String cargo, @RequestParam String empresa){
        return service.findAllCargoAndEmpresa(cargo, empresa);
    }

    @GetMapping("bio")
    public AutorInfo getFromBio(@RequestParam String bio){
        return service.findFromBio(bio);
    }

}