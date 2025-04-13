package com.bonam.crudmagico.api.controller;

import com.bonam.crudmagico.domain.dto.PersonagemDTO;
import com.bonam.crudmagico.domain.service.PersonagemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personagem")
@RequiredArgsConstructor
public class PersonagemController {

    private final PersonagemService personagemService;

    @GetMapping("/{id}")
    public ResponseEntity<PersonagemDTO> getPersonagem(@PathVariable Long id){
        return new ResponseEntity<>(personagemService.getPersonagemById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PersonagemDTO> createPersonagem(@RequestBody PersonagemDTO personagemDTO){
        return new ResponseEntity<>(personagemService.createPersonagem(personagemDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removePersonagem(@PathVariable Long id){
        personagemService.removePersonagem(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonagemDTO> editPersonagem(@PathVariable Long id, @RequestBody PersonagemDTO personagemDTO){
        return ResponseEntity.ok(personagemService.editPersonagem(id, personagemDTO));
    }
}
