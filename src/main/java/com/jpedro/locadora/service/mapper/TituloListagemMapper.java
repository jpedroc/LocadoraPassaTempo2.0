package com.jpedro.locadora.service.mapper;

import com.jpedro.locadora.domain.Titulo;
import com.jpedro.locadora.service.dto.TituloListagemDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TituloListagemMapper extends EntityMapper<TituloListagemDto, Titulo> {

    @Override
    @Mapping(target = "nomeDiretor", source = "diretor.nome")
    @Mapping(target = "nomeClasse", source = "classe.nome")
    TituloListagemDto toDto(Titulo titulo);
}
