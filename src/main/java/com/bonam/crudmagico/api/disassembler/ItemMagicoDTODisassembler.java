package com.bonam.crudmagico.api.disassembler;

import com.bonam.crudmagico.domain.dto.ItemMagicoDTO;
import com.bonam.crudmagico.domain.model.ItemMagico;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ItemMagicoDTODisassembler {
    public static ItemMagico toObject(ItemMagicoDTO itemMagicoDTO) {
        return new ItemMagico(
                itemMagicoDTO.getId(),
                itemMagicoDTO.getNome(),
                itemMagicoDTO.getTipoItem(),
                itemMagicoDTO.getForca(),
                itemMagicoDTO.getDefesa()
        );
    }
}
