package com.jpedro.locadora.service;

import com.jpedro.locadora.domain.Classe;
import com.jpedro.locadora.service.dto.DropDownDTO;
import com.jpedro.locadora.service.filter.ClasseFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClasseService {

    Classe save(Classe classe);

    Page<Classe> findAll(Pageable pageable, ClasseFilter classeFilter);

    Classe findById(Long id);

    void delete(Long id);

    List<DropDownDTO> findAllSelect();
}
