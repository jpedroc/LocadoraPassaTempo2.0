package com.jpedro.locadora.service.impl;

import com.jpedro.locadora.domain.Diretor;
import com.jpedro.locadora.repository.DiretorRepository;
import com.jpedro.locadora.service.DiretorService;
import com.jpedro.locadora.service.dto.DropDownDTO;
import com.jpedro.locadora.service.exception.BadRequestException;
import com.jpedro.locadora.service.filter.DiretorFilter;
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
public class DiretorServiceImpl implements DiretorService {

    private final DiretorRepository diretorRepository;

    @Override
    public Diretor save(Diretor diretor) {
        if(StringUtils.isBlank(diretor.getNome())) {
            throw new BadRequestException("Diretor deve ter um nome");
        }

        return diretorRepository.save(diretor);
    }

    @Override
    public Page<Diretor> findAll(Pageable pageable, DiretorFilter filter) {
        Page<Diretor> diretores = diretorRepository.findAll(filter.filter(), pageable);
        return diretores;
    }

    @Override
    public Diretor findById(Long id) {
        return findDiretor(id);
    }

    @Override
    public void delete(Long id) {
        Diretor diretor = findDiretor(id);
        diretorRepository.delete(diretor);
    }

    @Override
    public List<DropDownDTO> findAllSelect() {
        return diretorRepository.findAllSelect();
    }

    private Diretor findDiretor(Long id) {
        return diretorRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Diretor não encontrado"));
    }
}
