package com.pedro.financewatcher.repository;

import com.pedro.financewatcher.model.Despesa; // O IntelliJ deve importar isso sozinho
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long>{
// <Despesa, Long> significa: "Eu cuido da tabela Despesa, e a chave primária (ID) dela é do tipo Long".
}
