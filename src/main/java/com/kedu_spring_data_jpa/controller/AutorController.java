package com.kedu_spring_data_jpa.controller;

import com.kedu_spring_data_jpa.entity.AutorInfo;
import com.kedu_spring_data_jpa.service.AutorService;
import com.kedu_spring_data_jpa.entity.Autor;
import com.kedu_spring_data_jpa.projection.AutorInfoProjection;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("autores")
public class AutorController {

    private final AutorService service;

    public AutorController(AutorService service) {
        this.service = service;
    }

    @PostMapping
    public Autor create(@RequestBody Autor autor){
        service.save(autor);
        return autor;
    }

    @PutMapping
    public Autor update(@RequestBody Autor autor){
        service.update(autor);
        return autor;
    }

    @DeleteMapping("{id}")
    public String remove(@PathVariable Long id){
        service.delete(id);
        return "Removido";
    }

    @GetMapping("{id}")
    public Autor getById(@PathVariable Long id){
       return service.findById(id);
    }

    @GetMapping()
    public List<Autor> getAll(){
        return service.findAll();
    }

    @GetMapping("nomeOrSobreNome")
    public List<Autor> getAllByNomeOrSobreNome(@RequestParam String termo){
        return service.findAllByNomeOrSobreNome(termo);
    }

    @GetMapping("totalCadastrados")
    public Long getTotalElements(){
        return service.getTotalElements();
    }

    @PutMapping("{id}/info")
    public Autor updateInfoAutor(@PathVariable Long id, @RequestBody AutorInfo autorInfo){
        return service.saveInfoAutor(autorInfo, id);
    }

    @GetMapping("info")
    public List<Autor> getAllByCargo(@RequestParam String cargo) {
        return service.findByCargo(cargo);
    }

    @GetMapping("autor-info")
    public AutorInfoProjection getAllByCargo(@RequestParam Long id) {
        return service.findAutorInfoById(id);
    }

}
