package com.kedu_spring_data_jpa.projection;

import org.springframework.beans.factory.annotation.Value;

public interface AutorInfoProjection {

    @Value("#{target.nome + ' ' + target.sobreNome}")
    public String getNomeCompleto();

    public String getCargo();

    public String getBio();
}
