package com.kedu.demo_spring_rev_jpa.projection;

public class AutorInfoProjection {

    private String nomeCompleto;
    private String cargo;
    private String bio;

    public AutorInfoProjection(String nome, String sobreNome, String bio, String cargo) {
        this.nomeCompleto = nome +" " + sobreNome;
        this.bio = bio;
        this.cargo = cargo;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getCargo() {
        return cargo;
    }

    public String getBio() {
        return bio;
    }
}
