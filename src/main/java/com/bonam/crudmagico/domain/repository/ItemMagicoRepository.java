package com.bonam.crudmagico.domain.repository;

import com.bonam.crudmagico.domain.model.ItemMagico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemMagicoRepository extends JpaRepository<ItemMagico, Long> {

    List<ItemMagico> findByNomeContainingIgnoreCase(String nome);
}
