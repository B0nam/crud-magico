package com.bonam.crudmagico.domain.model;

import com.bonam.crudmagico.domain.types.TipoItem;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "ITEM_MAGICO")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class ItemMagico {

    @Id
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "TIPO_ITEM")
    private TipoItem tipoItem;

    @Column(name = "FORCA")
    private Integer forca;

    @Column(name = "DEFESA")
    private Integer defesa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personagem_id")
    private Personagem personagem;
}
