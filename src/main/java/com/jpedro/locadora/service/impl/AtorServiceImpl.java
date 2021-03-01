package com.jpedro.locadora.service.impl;

import com.jpedro.locadora.domain.Ator;
import com.jpedro.locadora.repository.AtorRepository;
import com.jpedro.locadora.service.AtorService;
import com.jpedro.locadora.service.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;

@Service
@Transactional
@RequiredArgsConstructor
public class AtorServiceImpl implements AtorService {

    private final AtorRepository atorRepository;

    @Override
    public Ator save(Ator ator) {
        if(StringUtils.isBlank(ator.getNome())) {
            throw new BadRequestException("Ator precisa de um nome");
        }

        return  atorRepository.save(ator);
    }

    @Override
    public Page<Ator> findAll(Pageable pageable) {
//        Page<Ator> atores = atorRepository.findAll(pageable);
        return null;
    }

    @Override
    public Ator findAtor(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
