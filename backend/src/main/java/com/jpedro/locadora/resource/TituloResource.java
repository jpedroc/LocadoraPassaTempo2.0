package com.jpedro.locadora.resource;

import com.jpedro.locadora.service.TituloService;
import com.jpedro.locadora.service.dto.DropDownDTO;
import com.jpedro.locadora.service.dto.TituloDto;
import com.jpedro.locadora.service.dto.TituloListagemDto;
import com.jpedro.locadora.service.filter.TituloFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/titulos")
@RequiredArgsConstructor
@Slf4j
public class TituloResource {

    private final TituloService tituloService;

    @PostMapping("search")
    public ResponseEntity<Page<TituloListagemDto>> listar(@RequestBody TituloFilter filter, Pageable pageable) {
        Page<TituloListagemDto> titulos = tituloService.findAll(filter, pageable);
        return new ResponseEntity<>(titulos, HttpStatus.OK);
    }

    @GetMapping("/select")
    public ResponseEntity<List<DropDownDTO>> listarSelect() {
        List<DropDownDTO> titulos = tituloService.findAllSelect();
        return new ResponseEntity<>(titulos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TituloDto> cadastrar(@Valid @RequestBody TituloDto titulo) {
        TituloDto tituloSalvo = tituloService.save(titulo);
        return ResponseEntity.created(URI.create("/api/titulos/" + tituloSalvo.getId())).body(tituloSalvo);
    }

    @PutMapping
    public ResponseEntity<TituloDto> alterar(@Valid @RequestBody TituloDto titulo) {
        TituloDto tituloEditado = tituloService.save(titulo);
        return new ResponseEntity<>(tituloEditado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        tituloService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
