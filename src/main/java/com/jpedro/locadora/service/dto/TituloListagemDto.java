package com.jpedro.locadora.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TituloListagemDto implements Serializable {
    private Long id;
    private String nome;
    private String categoria;
    private String nomeDiretor;
    private String nomeClasse;
    private List<String> nomeAtores;
}
