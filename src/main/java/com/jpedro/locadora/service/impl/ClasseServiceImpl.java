package com.jpedro.locadora.service.impl;

import com.jpedro.locadora.domain.Classe;
import com.jpedro.locadora.repository.ClasseRepository;
import com.jpedro.locadora.service.ClasseService;
import com.jpedro.locadora.service.dto.DropDownDTO;
import com.jpedro.locadora.service.exception.BadRequestException;
import com.jpedro.locadora.service.filter.ClasseFilter;
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
public class ClasseServiceImpl implements ClasseService {

    private final ClasseRepository classeRepository;

    @Override
    public Classe save(Classe classe) {
        if(StringUtils.isBlank(classe.getNome())) {
            throw new BadRequestException("Classe precisa de um nome");
        }

        if(classe.getValor().isNaN()) {
            throw new BadRequestException("Valor inválido");
        }

        if(classe.getPrazoDevolucao() == null) {
            throw new BadRequestException("Prazo de devolução inválido");
        }

        return  classeRepository.save(classe);
    }

    @Override
    public Page<Classe> findAll(Pageable pageable, ClasseFilter filter) {
        Page<Classe> classes = classeRepository.findAll(filter.filter(), pageable);
        return classes;
    }

    @Override
    public Classe findById(Long id) {
        return findClasse(id);
    }

    @Override
    public void delete(Long id) {
        Classe classe = findClasse(id);
        classeRepository.delete(classe);
    }

    @Override
    public List<DropDownDTO> findAllSelect() {
        return classeRepository.findAllSelect();
    }

    private Classe findClasse(Long id) {
        return classeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Classe não encontrado"));
    }
}
