package com.jpedro.locadora.service.mapper;

import com.jpedro.locadora.domain.Titulo;
import com.jpedro.locadora.service.dto.TituloDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TituloMapper extends EntityMapper<TituloDto, Titulo> {

    @Override
    @Mapping(target = "idDiretor", source = "diretor.id")
    @Mapping(target = "idClasse", source = "classe.id")
    TituloDto toDto(Titulo titulo);
}
