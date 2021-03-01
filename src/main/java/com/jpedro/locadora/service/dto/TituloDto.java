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
public class TituloDto implements Serializable {
    private Long id;
    private String name;
    private String categoria;
    private String sinopse;
    private Long idDiretor;
    private Long idClasse;
    private List<Long> idAtores;
}
