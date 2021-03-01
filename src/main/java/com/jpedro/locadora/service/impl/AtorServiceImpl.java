package com.jpedro.locadora.service.impl;

import com.jpedro.locadora.domain.Ator;
import com.jpedro.locadora.repository.AtorRepository;
import com.jpedro.locadora.service.AtorService;
import com.jpedro.locadora.service.dto.DropDownDTO;
import com.jpedro.locadora.service.exception.BadRequestException;
import com.jpedro.locadora.service.filter.AtorFilter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
    public Page<Ator> findAll(Pageable pageable, AtorFilter filter) {
        Page<Ator> atores = atorRepository.findAll(filter.filter(), pageable);
        return atores;
    }

    @Override
    public Ator findAtor(Long id) {
        return findById(id);
    }

    @Override
    public void delete(Long id) {
        Ator ator = findById(id);
        atorRepository.delete(ator);
    }

    @Override
    public List<DropDownDTO> findAllSelect() {
        return atorRepository.findAllSelect();
    }

    private Ator findById(Long id) {
        return atorRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Ator não encontrado"));
    }
}
