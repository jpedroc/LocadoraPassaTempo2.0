package com.jpedro.locadora.service;

import com.jpedro.locadora.domain.Ator;
import com.jpedro.locadora.service.dto.DropDownDTO;

import java.util.List;

public interface AtorService {

    Ator save(Ator ator);

    List<Ator> findAll();

    Ator findById(Long id);

    void delete(Long id);

    List<DropDownDTO> findAllSelect();
}
