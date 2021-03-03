package com.jpedro.locadora.resource;

import com.jpedro.locadora.domain.Ator;
import com.jpedro.locadora.service.AtorService;
import com.jpedro.locadora.service.dto.DropDownDTO;
import com.jpedro.locadora.service.filter.AtorFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/atores")
@RequiredArgsConstructor
@Slf4j
public class AtorResource {

    private final AtorService atorService;

    @PostMapping
    public ResponseEntity<Page<Ator>> listarAtores(@RequestBody AtorFilter filter, Pageable pageable) {
        Page<Ator> atores = atorService.findAll(pageable, filter);
        return new ResponseEntity<>(atores, HttpStatus.OK);
    }

    @GetMapping("/select")
    public ResponseEntity<List<DropDownDTO>> listarAtoresSelect() {
        List<DropDownDTO> atores = atorService.findAllSelect();
        return new ResponseEntity<>(atores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ator> listarAtoresSelect(@PathVariable Long id) {
        Ator ator = atorService.findById(id);
        return new ResponseEntity<>(ator, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Ator> cadastrarAtor(@Valid @RequestBody Ator ator) {
        Ator atorSalvo = atorService.save(ator);
        return ResponseEntity.created(URI.create("/api/atores/" + atorSalvo.getId())).body(atorSalvo);
    }

    @PutMapping
    public ResponseEntity<Ator> alterarAtor(@Valid @RequestBody Ator ator) {
        Ator atorEditado = atorService.save(ator);
        return new ResponseEntity<>(atorEditado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAtor(@PathVariable Long id) {
        atorService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
