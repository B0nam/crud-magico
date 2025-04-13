package com.bonam.crudmagico.domain.service;

import com.bonam.crudmagico.api.assembler.ItemMagicoDTOAssembler;
import com.bonam.crudmagico.api.disassembler.ItemMagicoDTODisassembler;
import com.bonam.crudmagico.domain.dto.ItemMagicoDTO;
import com.bonam.crudmagico.domain.model.ItemMagico;
import com.bonam.crudmagico.domain.repository.ItemMagicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemMagicoService {

    private final ItemMagicoRepository itemMagicoRepository;

    public List<ItemMagicoDTO> getAll() {
        return itemMagicoRepository.findAll()
                .stream()
                .map(ItemMagicoDTOAssembler::toDto)
                .toList();
    }

    public List<ItemMagicoDTO> searchByName(String nome) {
        return itemMagicoRepository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(ItemMagicoDTOAssembler::toDto)
                .toList();
    }

    public ItemMagicoDTO getItemMagicoById(Long id) {
        return itemMagicoRepository.findById(id)
                .map(ItemMagicoDTOAssembler::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item Mágico não encontrado"));
    }

    public ItemMagicoDTO createItemMagico(ItemMagicoDTO itemMagicoDTO) {
        ItemMagico itemMagico = ItemMagicoDTODisassembler.toObject(itemMagicoDTO);
        return ItemMagicoDTOAssembler.toDto(itemMagicoRepository.save(itemMagico));
    }

    public void removeItemMagico(Long id) {
        ItemMagico itemMagico = itemMagicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item Mágico não encontrado"));
        itemMagicoRepository.delete(itemMagico);
    }

    public ItemMagicoDTO editItemMagico(Long id, ItemMagicoDTO itemMagicoDTO) {
        ItemMagico itemMagico = itemMagicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item Mágico não encontrado"));

        itemMagico.setNome(itemMagicoDTO.getNome());
        itemMagico.setTipoItem(itemMagicoDTO.getTipoItem());
        itemMagico.setForca(itemMagicoDTO.getForca());
        itemMagico.setDefesa(itemMagicoDTO.getDefesa());

        return ItemMagicoDTOAssembler.toDto(itemMagicoRepository.save(itemMagico));
    }
}
