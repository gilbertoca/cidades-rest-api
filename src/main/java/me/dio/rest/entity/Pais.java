package me.dio.rest.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Pais implements Serializable {
    @Id
    private Long id;
    private String nome;
    private String nomePt;
    private String sigla;
    private Integer bacen;
    
    public Pais(){}

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getNomePt() {
        return nomePt;
    }

    public String getSigla() {
        return sigla;
    }

    public Integer getBacen() {
        return bacen;
    }
}
