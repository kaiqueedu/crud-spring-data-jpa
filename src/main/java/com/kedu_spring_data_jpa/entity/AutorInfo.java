package com.kedu_spring_data_jpa.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class AutorInfo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cargo", length = 45, nullable = false)
    private String cargo;

    @Column(name = "bio", length = 255, nullable = true)
    private String bio;

    @Column(name = "empresa", length = 45, nullable = false)
    private String empresa;

    public void setId(Long id) { this.id = id; };

    public Long getId() {
        return id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AutorInfo autorInfo = (AutorInfo) o;
        return Objects.equals(id, autorInfo.id) && Objects.equals(cargo, autorInfo.cargo) && Objects.equals(bio, autorInfo.bio);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
