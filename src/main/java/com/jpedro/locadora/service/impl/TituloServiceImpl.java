package com.jpedro.locadora.service.impl;

import com.jpedro.locadora.domain.Titulo;
import com.jpedro.locadora.repository.TituloRepository;
import com.jpedro.locadora.service.TituloService;
import com.jpedro.locadora.service.dto.DropDownDTO;
import com.jpedro.locadora.service.dto.TituloDto;
import com.jpedro.locadora.service.dto.TituloListagemDto;
import com.jpedro.locadora.service.exception.BadRequestException;
import com.jpedro.locadora.service.filter.TituloFilter;
import com.jpedro.locadora.service.mapper.TituloListagemMapper;
import com.jpedro.locadora.service.mapper.TituloMapper;
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
public class TituloServiceImpl implements TituloService {

    private final TituloRepository tituloRepository;
    private final TituloMapper tituloMapper;
    private final TituloListagemMapper tituloListagemMapper;

    @Override
    public Page<TituloListagemDto> findAll(TituloFilter filter, Pageable pageable) {
        Page<Titulo> titulos = tituloRepository.findAll(filter.filter(), pageable);
        return titulos.map(tituloListagemMapper::toDto);
    }

    @Override
    public List<DropDownDTO> findAllSelect() {
        return tituloRepository.findAllSelect();
    }

    @Override
    public TituloDto findById(Long id) {
        return tituloMapper.toDto(findTitulo(id));
    }

    @Override
    public TituloDto save(TituloDto tituloDto) {
        Titulo titulo = tituloMapper.toEntity(tituloDto);
        validarTitulo(titulo);

        return tituloMapper.toDto(tituloRepository.save(titulo));
    }

    @Override
    public void delete(Long id) {
        Titulo titulo = findTitulo(id);
        tituloRepository.delete(titulo);
    }

    private Titulo findTitulo(Long id) {
        return tituloRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Título não encontrado."));
    }

    private void validarTitulo(Titulo titulo) {
        if(StringUtils.isBlank(titulo.getNome())) {
            throw new BadRequestException("Título precisa de um nome");
        }

        if(StringUtils.isBlank(titulo.getCategoria())) {
            throw new BadRequestException("Título precisa de uma categoria");
        }

        if(titulo.getDiretor() == null) {
            throw new BadRequestException("Título precisa de um diretor");
        }

        if(titulo.getClasse() == null) {
            throw new BadRequestException("Título precisa de uma classe");
        }

        if(titulo.getAtores().isEmpty()) {
            throw new BadRequestException("Título precisa de atores");
        }
    }
}
