package com.jpedro.locadora.service;

import com.jpedro.locadora.domain.Titulo;
import com.jpedro.locadora.service.dto.DropDownDTO;
import com.jpedro.locadora.service.dto.TituloDto;
import com.jpedro.locadora.service.dto.TituloListagemDto;
import com.jpedro.locadora.service.filter.TituloFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TituloService {
    Page<TituloListagemDto> findAll(TituloFilter filter, Pageable pageable);

    List<DropDownDTO> findAllSelect();

    TituloDto findById(Long id);

    TituloDto save(TituloDto tituloDto);

    void delete(Long id);
}
