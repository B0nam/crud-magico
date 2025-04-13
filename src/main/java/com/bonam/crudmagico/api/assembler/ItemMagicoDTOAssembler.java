package com.bonam.crudmagico.api.assembler;

import com.bonam.crudmagico.domain.dto.ItemMagicoDTO;
import com.bonam.crudmagico.domain.model.ItemMagico;
import lombok.experimental.UtilityClass;


@UtilityClass
public class ItemMagicoDTOAssembler {
    public static ItemMagicoDTO toDto(ItemMagico itemMagico) {
        return ItemMagicoDTO.builder()
                .id(itemMagico.getId())
                .nome(itemMagico.getNome())
                .tipoItem(itemMagico.getTipoItem())
                .forca(itemMagico.getForca())
                .defesa(itemMagico.getDefesa())
                .build();
    }
}
