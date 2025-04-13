package com.bonam.crudmagico.api.controller;

import com.bonam.crudmagico.domain.dto.ItemMagicoDTO;
import com.bonam.crudmagico.domain.dto.PersonagemDTO;
import com.bonam.crudmagico.domain.service.PersonagemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/{id}/item")
    public ResponseEntity<PersonagemDTO> addItemPersonagem(@PathVariable Long id, @RequestBody Long idItem){
        return ResponseEntity.ok(personagemService.addItemPersonagem(id, idItem));
    }

    @GetMapping("/{id}/itens")
    public ResponseEntity<List<ItemMagicoDTO>> getItensByPersonagem(@PathVariable Long id) {
        return ResponseEntity.ok(personagemService.getItensByPersonagem(id));
    }

    @DeleteMapping("/{id}/item/{idItem}")
    public ResponseEntity<Void> removerItemPersonagem(@PathVariable Long id, @PathVariable Long idItem) {
        personagemService.removerItemPersonagem(id, idItem);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/amuleto")
    public ResponseEntity<ItemMagicoDTO> getAmuleto(@PathVariable Long id) {
        return ResponseEntity.ok(personagemService.buscarAmuleto(id));
    }

    @PatchMapping("/{id}/nome-aventureiro")
    public ResponseEntity<Void> atualizarNomeAventureiro(@PathVariable Long id, @RequestBody String novoNome) {
        personagemService.atualizarNomeAventureiro(id, novoNome);
        return ResponseEntity.noContent().build();
    }
}
