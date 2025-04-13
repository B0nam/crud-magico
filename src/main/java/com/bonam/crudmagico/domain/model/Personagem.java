package com.bonam.crudmagico.domain.model;

import com.bonam.crudmagico.domain.types.ClassePersonagem;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "PERSONAGEM")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Personagem {

    @Id
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "NOME_AVENTUREIRO")
    private String nomeAventureiro;

    @Column(name = "CLASSE_PERSONAGEM")
    private ClassePersonagem classePersonagem;

    @OneToMany(mappedBy = "personagem", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemMagico> itensMagicos;

    @Column(name = "LEVEL")
    private Integer level;

    @Column(name = "FORCA")
    private Integer forca;

    @Column(name = "DEFESA")
    private Integer defesa;
}
