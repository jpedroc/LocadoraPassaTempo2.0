package com.jpedro.locadora.repository;

import com.jpedro.locadora.domain.Ator;
import com.jpedro.locadora.service.dto.DropDownDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtorRepository extends JpaRepository<Ator, Long>, JpaSpecificationExecutor<Ator> {

    @Query("SELECT new com.jpedro.locadora.service.dto.DropDownDTO(a.id, a.nome) from Ator")
    List<DropDownDTO> findAllSelect();
}
