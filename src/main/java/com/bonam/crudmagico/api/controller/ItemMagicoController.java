package com.bonam.crudmagico.api.controller;

import com.bonam.crudmagico.domain.dto.ItemMagicoDTO;
import com.bonam.crudmagico.domain.service.ItemMagicoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemMagicoController {

    private final ItemMagicoService itemMagicoService;  // Injeção do service

    @GetMapping("/{id}")
    public ResponseEntity<ItemMagicoDTO> getItemMagico(@PathVariable Long id) {
        return new ResponseEntity<>(itemMagicoService.getItemMagicoById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ItemMagicoDTO> createItemMagico(@RequestBody ItemMagicoDTO itemMagicoDTO) {
        return new ResponseEntity<>(itemMagicoService.createItemMagico(itemMagicoDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeItemMagico(@PathVariable Long id) {
        itemMagicoService.removeItemMagico(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemMagicoDTO> editItemMagico(@PathVariable Long id, @RequestBody ItemMagicoDTO itemMagicoDTO) {
        return ResponseEntity.ok(itemMagicoService.editItemMagico(id, itemMagicoDTO));
    }
}
