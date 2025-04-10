package com.bonam.crudmagico.domain.model;

import com.bonam.crudmagico.domain.types.TipoItem;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity(name = "ITEM_MAGICO")
@AllArgsConstructor
@NoArgsConstructor
public class ItemMagico {

    @Id
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "TIPO_ITEM")
    private TipoItem tIpoItem;

    @Column(name = "FORCA")
    private Integer forca;

    @Column(name = "DEFESA")
    private Integer defesa;
}
