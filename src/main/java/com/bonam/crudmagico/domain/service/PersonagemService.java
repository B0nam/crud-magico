package com.bonam.crudmagico.domain.service;

import com.bonam.crudmagico.api.assembler.ItemMagicoDTOAssembler;
import com.bonam.crudmagico.api.assembler.PersonagemDTOAssembler;
import com.bonam.crudmagico.api.disassembler.PersonagemDTODisassembler;
import com.bonam.crudmagico.domain.dto.ItemMagicoDTO;
import com.bonam.crudmagico.domain.dto.PersonagemDTO;
import com.bonam.crudmagico.domain.model.ItemMagico;
import com.bonam.crudmagico.domain.model.Personagem;
import com.bonam.crudmagico.domain.repository.ItemMagicoRepository;
import com.bonam.crudmagico.domain.repository.PersonagemRepository;
import com.bonam.crudmagico.domain.types.TipoItem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonagemService {

    private final PersonagemRepository personagemRepository;
    private final ItemMagicoRepository itemMagicoRepository;

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

    public PersonagemDTO addItemPersonagem(Long id, Long idItem) {
        Personagem personagem = personagemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Personagem não encontrado"));

        ItemMagico itemMagico = itemMagicoRepository.findById(idItem)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item Mágico não encontrado"));

        if (itemMagico.getForca() == 0 && itemMagico.getDefesa() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item não pode ter força e defesa iguais a 0.");
        }

        switch (itemMagico.getTipoItem()) {
            case ARMA -> {
                if (itemMagico.getDefesa() != 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Armas devem ter defesa igual a 0.");
                }
            }
            case ARMADURA -> {
                if (itemMagico.getForca() != 0) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Armaduras devem ter força igual a 0.");
                }
            }
            case AMULETO -> {
                boolean jaTemAmuleto = personagem.getItensMagicos().stream()
                        .anyMatch(i -> i.getTipoItem().equals(TipoItem.AMULETO));

                if (jaTemAmuleto) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Personagem já possui um amuleto.");
                }
            }
        }

        personagem.getItensMagicos().add(itemMagico);
        personagemRepository.save(personagem);
        return PersonagemDTOAssembler.toDto(personagem);
    }

    public List<ItemMagicoDTO> getItensByPersonagem(Long id) {
        Personagem personagem = personagemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Personagem não encontrado"));

        return personagem.getItensMagicos().stream().map(ItemMagicoDTOAssembler::toDto).toList();
    }

    public void removerItemPersonagem(Long id, Long idItem) {
        Personagem personagem = personagemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Personagem não encontrado"));

        ItemMagico itemMagico = itemMagicoRepository.findById(idItem)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item Mágico não encontrado"));

        if (!personagem.getItensMagicos().remove(itemMagico)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item não encontrado no personagem");
        }

        personagemRepository.save(personagem);
    }

    public ItemMagicoDTO buscarAmuleto(Long id) {
        Personagem personagem = personagemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Personagem não encontrado"));

        return personagem.getItensMagicos().stream()
                .filter(item -> item.getTipoItem().equals(TipoItem.AMULETO))
                .findFirst()
                .map(ItemMagicoDTOAssembler::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Amuleto não encontrado no personagem"));
    }

    public void atualizarNomeAventureiro(Long id, String novoNome) {
        Personagem personagem = personagemRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Personagem não encontrado"));

        personagem.setNomeAventureiro(novoNome);
        personagemRepository.save(personagem);
    }
}
