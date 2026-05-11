package com.kedu.demo_spring_rev_jpa.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class InfoAutor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cargo", length = 45, nullable = false)
    private String cargo;

    @Column(name = "bio", length = 255, nullable = true)
    private String bio;

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        InfoAutor infoAutor = (InfoAutor) o;
        return Objects.equals(id, infoAutor.id) && Objects.equals(cargo, infoAutor.cargo) && Objects.equals(bio, infoAutor.bio);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
