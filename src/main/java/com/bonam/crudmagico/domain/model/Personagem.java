package com.bonam.crudmagico.domain.model;

import com.bonam.crudmagico.domain.types.ClassePersonagem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.engine.internal.Cascade;

import java.util.List;

@Entity(name = "PERSONAGEM")
@AllArgsConstructor
@NoArgsConstructor
public class Personagem {

    @Id
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "NOME_AVENTUREIRO")
    private String nomeAventureiro;

    @Column(name = "CLASSE_PERSONAGEM")
    private ClassePersonagem classePersonagem;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ItemMagico> itensMagicos;

    @Column(name = "LEVEL")
    private Integer level;

    @Column(name = "FORCA")
    private Integer forca;

    @Column(name = "DEFESA")
    private Integer defesa;
}
