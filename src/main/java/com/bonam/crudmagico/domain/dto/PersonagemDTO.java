package com.bonam.crudmagico.domain.dto;

import com.bonam.crudmagico.domain.types.ClassePersonagem;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class PersonagemDTO {
    private Long id;
    private String nome;
    private String nomeAventureiro;
    private ClassePersonagem classePersonagem;
    private List<ItemMagicoDTO> itensMagicos;
    private Integer level;
    private Integer forca;
    private Integer defesa;
}
