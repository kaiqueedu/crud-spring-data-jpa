package com.kedu.demo_spring_rev_jpa.controller;

import com.kedu.demo_spring_rev_jpa.dao.AutorDao;
import com.kedu.demo_spring_rev_jpa.entity.Autor;
import com.kedu.demo_spring_rev_jpa.entity.InfoAutor;
import com.kedu.demo_spring_rev_jpa.projection.AutorInfoProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("autores")
public class AutorController {

    @Autowired
    private AutorDao dao;

    @PostMapping
    public Autor create(@RequestBody Autor autor){
        dao.save(autor);
        return autor;
    }

    @PutMapping
    public Autor update(@RequestBody Autor autor){
        dao.update(autor);
        return autor;
    }

    @DeleteMapping("{id}")
    public String remove(@PathVariable Long id){
        dao.delete(id);
        return "Removido";
    }

    @GetMapping("{id}")
    public Autor getById(@PathVariable Long id){
       return dao.findById(id);
    }

    @GetMapping()
    public List<Autor> getAll(){
        return dao.findAll();
    }

    @GetMapping("nomeOrSobreNome")
    public List<Autor> getAllByNomeOrSobreNome(@RequestParam String termo){
        return dao.findAllByNomeOrSobreNome(termo);
    }

    @GetMapping("totalCadastrados")
    public Long getTotalElements(){
        return dao.getTotalElements();
    }

    @PutMapping("{id}/info")
    public Autor updateInfoAutor(@PathVariable Long id, @RequestBody InfoAutor infoAutor){
        return dao.saveInfoAutor(infoAutor, id);
    }

    @GetMapping("info")
    public List<Autor> getAllByCargo(@RequestParam String cargo) {
        return dao.findByCargo(cargo);
    }

    @GetMapping("autor-info")
    public AutorInfoProjection getAllByCargo(@RequestParam Long id) {
        return dao.findAutorInfoById(id);
    }

}
