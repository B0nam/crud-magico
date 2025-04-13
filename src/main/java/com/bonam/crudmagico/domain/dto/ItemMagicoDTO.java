package com.bonam.crudmagico.domain.dto;

import com.bonam.crudmagico.domain.types.TipoItem;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ItemMagicoDTO {
    private Long id;
    private String nome;
    private TipoItem tipoItem;
    private Integer forca;
    private Integer defesa;
    private Long personagemId;
}
