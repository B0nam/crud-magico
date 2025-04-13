package com.bonam.crudmagico.api.assembler;

import com.bonam.crudmagico.domain.dto.PersonagemDTO;
import com.bonam.crudmagico.domain.model.Personagem;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PersonagemDTOAssembler {
    public static PersonagemDTO toDto(Personagem personagem) {
        return PersonagemDTO.builder()
                .id(personagem.getId())
                .nome(personagem.getNome())
                .nomeAventureiro(personagem.getNomeAventureiro())
                .classePersonagem(personagem.getClassePersonagem())
                .itensMagicos(personagem.getItensMagicos())
                .level(personagem.getLevel())
                .forca(personagem.getForca())
                .defesa(personagem.getDefesa())
                .build();
    }
}
