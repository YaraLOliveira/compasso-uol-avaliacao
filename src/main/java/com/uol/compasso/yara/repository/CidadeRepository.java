package com.uol.compasso.yara.repository;

import com.uol.compasso.yara.enuns.EnumEstado;
import com.uol.compasso.yara.model.Cidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

    @Query(value="SELECT c from Cidade c WHERE upper(c.nome) = upper(:nome) ")
    Page<Cidade> page(@Param("nome") String nome,
                      @Param("pageable") Pageable pageable);
    }
