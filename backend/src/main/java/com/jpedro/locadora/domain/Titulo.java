package com.jpedro.locadora.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "TITULO")
@Getter
@Setter
public class Titulo implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "CATEGORIA")
    private String categoria;

    @Column(name = "SINOPSE")
    private String sinopse;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_DIRETOR", referencedColumnName = "id")
    private Diretor diretor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ID_CLASSE", referencedColumnName = "id")
    private Classe classe;

    @ManyToMany
    @JoinTable(name = "TITULO_ATORES", joinColumns = @JoinColumn(name = "TITULO_ID"), inverseJoinColumns = @JoinColumn(name = "ATOR_ID"))
    private List<Ator> atores;
}
