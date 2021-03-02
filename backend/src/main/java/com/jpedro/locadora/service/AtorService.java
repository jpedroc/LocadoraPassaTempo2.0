package com.jpedro.locadora.service;

import com.jpedro.locadora.domain.Ator;
import com.jpedro.locadora.service.dto.DropDownDTO;
import com.jpedro.locadora.service.filter.AtorFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AtorService {

    Ator save(Ator ator);

    Page<Ator> findAll(Pageable pageable, AtorFilter atorFilter);

    Ator findById(Long id);

    void delete(Long id);

    List<DropDownDTO> findAllSelect();
}
