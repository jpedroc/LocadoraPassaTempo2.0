package com.jpedro.locadora.resource;

import com.jpedro.locadora.domain.Ator;
import com.jpedro.locadora.domain.Classe;
import com.jpedro.locadora.service.AtorService;
import com.jpedro.locadora.service.ClasseService;
import com.jpedro.locadora.service.dto.DropDownDTO;
import com.jpedro.locadora.service.filter.AtorFilter;
import com.jpedro.locadora.service.filter.ClasseFilter;
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
@RequestMapping("/api/classes")
@RequiredArgsConstructor
@Slf4j
public class ClasseResource {

    private final ClasseService classeService;

    @GetMapping
    public ResponseEntity<Page<Classe>> listarClasses(@ModelAttribute ClasseFilter filter, Pageable pageable) {
        Page<Classe> classes = classeService.findAll(pageable, filter);
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @GetMapping("/select")
    public ResponseEntity<List<DropDownDTO>> listarClassesSelect() {
        List<DropDownDTO> classes = classeService.findAllSelect();
        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classe> listarClassesSelect(@PathVariable Long id) {
        Classe classe = classeService.findClasse(id);
        return new ResponseEntity<>(classe, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Classe> cadastrarClasse(@Valid @RequestBody Classe classe) {
        Classe classeSalve = classeService.save(classe);
        return ResponseEntity.created(URI.create("/api/classes/" + classeSalve.getId())).body(classeSalve);
    }

    @PutMapping
    public ResponseEntity<Classe> alterarClasse(@Valid @RequestBody Classe classe) {
        Classe classeEditada = classeService.save(classe);
        return new ResponseEntity<>(classeEditada, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirClasse(@PathVariable Long id) {
        classeService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
