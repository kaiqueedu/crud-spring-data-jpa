package com.kedu.demo_spring_rev_jpa.dao;

import com.kedu.demo_spring_rev_jpa.entity.Autor;
import com.kedu.demo_spring_rev_jpa.entity.InfoAutor;
import com.kedu.demo_spring_rev_jpa.projection.AutorInfoProjection;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AutorDao {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    public void save(Autor autor){
        this.manager.persist(autor);
    }

    @Transactional
    public void update(Autor autor){
        this.manager.merge(autor);
    }

    @Transactional
    public void delete(Long id){
        this.manager.remove(this.manager.getReference(Autor.class, id));
    }

    @Transactional(readOnly = true)
    public Autor findById(Long id){
        return this.manager.find(Autor.class, id);
    }

    @Transactional(readOnly = true)
    public List<Autor> findAll(){
        String query = "SELECT a FROM Autor a";
        return this.manager.createQuery(query, Autor.class).getResultList();
    }

    @Transactional(readOnly = true)
    public List<Autor> findAllByNomeOrSobreNome(String termo){
        String query = " SELECT a FROM Autor a WHERE a.nome like :termo OR a.sobreNome like :termo";

        return this.manager.createQuery(query, Autor.class)
                .setParameter("termo", "%" + termo + "%")
                .getResultList();
    }

    @Transactional(readOnly = true)
    public Long getTotalElements(){
        String query = "SELECT count(1) FROM Autor a";
        return this.manager.createQuery(query, Long.class).getSingleResult();

    }

    @Transactional(readOnly = false)
    public Autor saveInfoAutor(InfoAutor inforAutor, Long autorId){
        Autor autor = findById(autorId);
        autor.setInfoAutor(inforAutor);
        return autor;
    }

    @Transactional(readOnly = true)
    public List<Autor> findByCargo(String cargo){
       String query = """
               SELECT a autor FROM Autor a
               WHERE a.infoAutor.cargo like :cargo
               ORDER BY a.nome asc
               """;
       return this.manager.createQuery(query, Autor.class)
               .setParameter("cargo", "%" + cargo + "%")
               .getResultList();
    }

    @Transactional(readOnly = true)
    public AutorInfoProjection findAutorInfoById(Long id){
        String query = """
                   SELECT new AutorInfoProjection(a.nome,  a.sobreNome, a.infoAutor.cargo, a.infoAutor.bio)
                   FROM Autor a
                   WHERE a.id = :id
              """;
        return this.manager.createQuery(query, AutorInfoProjection.class)
                .setParameter("id", id)
                .getSingleResult();
    }

}
