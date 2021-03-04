package com.jpedro.locadora.resource;

import com.jpedro.locadora.domain.Classe;
import com.jpedro.locadora.domain.Diretor;
import com.jpedro.locadora.service.ClasseService;
import com.jpedro.locadora.service.DiretorService;
import com.jpedro.locadora.service.dto.DropDownDTO;
import com.jpedro.locadora.service.filter.ClasseFilter;
import com.jpedro.locadora.service.filter.DiretorFilter;
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
@RequestMapping("/api/diretores")
@RequiredArgsConstructor
@Slf4j
public class DiretorResource {

    private final DiretorService diretorService;

    @PostMapping("search")
    public ResponseEntity<Page<Diretor>> listarDiretores(@RequestBody DiretorFilter filter, Pageable pageable) {
        Page<Diretor> diretores = diretorService.findAll(pageable, filter);
        return new ResponseEntity<>(diretores, HttpStatus.OK);
    }

    @GetMapping("/select")
    public ResponseEntity<List<DropDownDTO>> listarDiretoresSelect() {
        List<DropDownDTO> diretores = diretorService.findAllSelect();
        return new ResponseEntity<>(diretores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diretor> listarDiretoresSelect(@PathVariable Long id) {
        Diretor diretor = diretorService.findById(id);
        return new ResponseEntity<>(diretor, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Diretor> cadastrarDiretor(@Valid @RequestBody Diretor diretor) {
        Diretor diretorSalvo = diretorService.save(diretor);
        return ResponseEntity.created(URI.create("/api/diretores/" + diretorSalvo.getId())).body(diretorSalvo);
    }

    @PutMapping
    public ResponseEntity<Diretor> alterarDiretor(@Valid @RequestBody Diretor diretor) {
        Diretor diretorEditado = diretorService.save(diretor);
        return new ResponseEntity<>(diretorEditado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDiretor(@PathVariable Long id) {
        diretorService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
