package com.kedu_spring_data_jpa.service;

import com.kedu_spring_data_jpa.entity.Autor;
import com.kedu_spring_data_jpa.entity.AutorInfo;
import com.kedu_spring_data_jpa.projection.AutorInfoProjection;
import com.kedu_spring_data_jpa.repository.AutorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AutorService {

    @PersistenceContext
    private EntityManager manager;

    private final AutorRepository repository;

    public AutorService(AutorRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void save(Autor autor){
        repository.save(autor);
    }

    @Transactional
    public void update(Autor autor){
        repository.save(autor);
    }

    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Autor findById(Long id){
        return repository.findById(id).orElseThrow( () -> new RuntimeException("Id não encontrado"));
    }

    @Transactional(readOnly = true)
    public List<Autor> findAll(){
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Autor> findAllByNomeOrSobreNome(String termo){
        return repository.findAllByNomeOrSobreNome("%" + termo + "%");
    }

    @Transactional(readOnly = true)
    public Long getTotalElements(){
       return repository.count();
    }

    @Transactional(readOnly = false)
    public Autor saveInfoAutor(AutorInfo autorInfo, Long autorId){
        Autor autor = findById(autorId);
        autor.setInfoAutor(autorInfo);
        return autor;
    }

    @Transactional(readOnly = true)
    public List<Autor> findByCargo(String cargo){
        return repository.findByCargo(cargo);
    }

    @Transactional(readOnly = true)
    public AutorInfoProjection findAutorInfoById(Long id){
        return repository.findAutorInfoById(id);

    }

}
