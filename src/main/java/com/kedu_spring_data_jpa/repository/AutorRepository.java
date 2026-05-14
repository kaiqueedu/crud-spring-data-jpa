package com.kedu_spring_data_jpa.repository;

import com.kedu_spring_data_jpa.entity.Autor;
import com.kedu_spring_data_jpa.projection.AutorInfoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query(
            """
            SELECT a FROM Autor a
            WHERE a.autorInfo.cargo like :cargo
            ORDER BY a.nome asc
            """
    )
    List<Autor> findByCargo(@Param("cargo") String cargo);

    @Query(
            """
            SELECT a FROM Autor a
            WHERE a.nome like :termo
            OR a.sobreNome like :termo
            """
    )
    List<Autor> findAllByNomeOrSobreNome(@Param("termo") String termo);

    @Query(
            """ 
            SELECT a.nome as nome,  a.sobreNome as sobreNome, a.autorInfo.cargo as cargo, a.autorInfo.bio as bio
            FROM Autor a
            WHERE a.id = :id
            """
    )
    AutorInfoProjection findAutorInfoById(@Param("id") Long id);
}
