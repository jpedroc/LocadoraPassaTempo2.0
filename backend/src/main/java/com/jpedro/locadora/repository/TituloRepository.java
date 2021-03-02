package com.jpedro.locadora.repository;

import com.jpedro.locadora.domain.Titulo;
import com.jpedro.locadora.service.dto.DropDownDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TituloRepository extends JpaRepository<Titulo, Long>, JpaSpecificationExecutor<Titulo> {

    @Query("SELECT new com.jpedro.locadora.service.dto.DropDownDTO(t.id, t.nome) FROM Titulo t")
    List<DropDownDTO> findAllSelect();
}
