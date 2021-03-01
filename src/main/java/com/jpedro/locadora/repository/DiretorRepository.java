package com.jpedro.locadora.repository;

import com.jpedro.locadora.domain.Diretor;
import com.jpedro.locadora.service.dto.DropDownDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiretorRepository extends JpaRepository<Diretor, Long>, JpaSpecificationExecutor<Diretor> {

    @Query("SELECT new com.jpedro.locadora.service.dto.DropDownDTO(d.id, d.nome) FROM Diretor")
    List<DropDownDTO> findAllSelect();
}
