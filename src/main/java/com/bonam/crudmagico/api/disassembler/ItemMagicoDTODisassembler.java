package com.bonam.crudmagico.api.disassembler;

import com.bonam.crudmagico.domain.dto.ItemMagicoDTO;
import com.bonam.crudmagico.domain.model.ItemMagico;
import com.bonam.crudmagico.domain.model.Personagem;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ItemMagicoDTODisassembler {

    public static ItemMagico toObject(ItemMagicoDTO itemMagicoDTO) {
        ItemMagico item = ItemMagico.builder()
                .id(itemMagicoDTO.getId())
                .nome(itemMagicoDTO.getNome())
                .tipoItem(itemMagicoDTO.getTipoItem())
                .forca(itemMagicoDTO.getForca())
                .defesa(itemMagicoDTO.getDefesa())
                .build();

        if (itemMagicoDTO.getPersonagemId() != null) {
            item.setPersonagem(Personagem.builder()
                    .id(itemMagicoDTO.getPersonagemId())
                    .build());
        }

        return item;
    }
}
