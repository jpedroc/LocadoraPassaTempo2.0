package com.jpedro.locadora.service;

import com.jpedro.locadora.domain.Diretor;
import com.jpedro.locadora.service.dto.DropDownDTO;
import com.jpedro.locadora.service.filter.DiretorFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DiretorService {

    Diretor save(Diretor diretor);

    Page<Diretor> findAll(Pageable pageable, DiretorFilter atorFilter);

    Diretor findById(Long id);

    void delete(Long id);

    List<DropDownDTO> findAllSelect();
}
