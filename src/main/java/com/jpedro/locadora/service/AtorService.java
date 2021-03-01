package com.jpedro.locadora.service;

import com.jpedro.locadora.domain.Ator;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

public interface AtorService {

    Ator save(Ator ator);

    Page<Ator> findAll(Pageable pageable);

    Ator findAtor(Long id);

    void delete(Long id);
}
