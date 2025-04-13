package com.bonam.crudmagico.api.disassembler;

import com.bonam.crudmagico.domain.dto.PersonagemDTO;
import com.bonam.crudmagico.domain.model.Personagem;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PersonagemDTODisassembler {
    public static Personagem toObject(PersonagemDTO personagemDto) {
        return Personagem.builder()
                .nome(personagemDto.getNome())
                .nomeAventureiro(personagemDto.getNomeAventureiro())
                .classePersonagem(personagemDto.getClassePersonagem())
                .itensMagicos(personagemDto.getItensMagicos())
                .level(personagemDto.getLevel())
                .forca(personagemDto.getForca())
                .defesa(personagemDto.getDefesa())
                .build();
    }
}
