package com.bonam.crudmagico.domain.service;

import com.bonam.crudmagico.api.assembler.PersonagemDTOAssembler;
import com.bonam.crudmagico.api.disassembler.PersonagemDTODisassembler;
import com.bonam.crudmagico.domain.dto.PersonagemDTO;
import com.bonam.crudmagico.domain.model.Personagem;
import com.bonam.crudmagico.domain.repository.PersonagemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonagemService {

    private final PersonagemRepository personagemRepository;

    public List<PersonagemDTO> getPersonagens() {
        return personagemRepository.findAll().stream()
                .map(PersonagemDTOAssembler::toDto)
                .toList();
    }

    public PersonagemDTO getPersonagemById(Long id) {
        return personagemRepository.findById(id)
                .map(PersonagemDTOAssembler::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Personagem não encontrado"));
    }

    public PersonagemDTO createPersonagem(PersonagemDTO personagemDTO) {
        validarPontuacao(personagemDTO);
        Personagem personagem = PersonagemDTODisassembler.toObject(personagemDTO);
        return PersonagemDTOAssembler.toDto(personagemRepository.save(personagem));
    }

    public void removePersonagem(Long id) {
        Personagem personagem = personagemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Personagem não encontrado"));

        personagemRepository.delete(personagem);
    }

    public PersonagemDTO editPersonagem(Long id, PersonagemDTO personagemDTO) {
        validarPontuacao(personagemDTO);
        Personagem personagem = personagemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Personagem não encontrado"));

        personagem.setNome(personagemDTO.getNome());
        personagem.setNomeAventureiro(personagemDTO.getNomeAventureiro());
        personagem.setClassePersonagem(personagemDTO.getClassePersonagem());
        personagem.setLevel(personagemDTO.getLevel());
        personagem.setForca(personagemDTO.getForca());
        personagem.setDefesa(personagemDTO.getDefesa());

        return PersonagemDTOAssembler.toDto(personagemRepository.save(personagem));
    }

    private void validarPontuacao(PersonagemDTO personagemDTO) {
        int totalPontos = personagemDTO.getForca() + personagemDTO.getDefesa();
        if (totalPontos != 10) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A soma dos pontos de Força e Defesa deve ser igual a 10.");
        }
    }
}
