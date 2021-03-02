package com.jpedro.locadora.repository;

import com.jpedro.locadora.domain.Classe;
import com.jpedro.locadora.service.dto.DropDownDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long>, JpaSpecificationExecutor<Classe> {

    @Query("SELECT new com.jpedro.locadora.service.dto.DropDownDTO(c.id, c.nome) FROM Classe c")
    List<DropDownDTO> findAllSelect();
}
